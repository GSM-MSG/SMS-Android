package com.sms.presentation.main.viewmodel

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.authentication.MarkingBoardType
import com.msg.sms.domain.model.authentication.request.AtomicAuthenticationFieldModel
import com.msg.sms.domain.model.authentication.request.AuthenticationFieldModel
import com.msg.sms.domain.model.authentication.request.AuthenticationObjectModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationFormModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel
import com.msg.sms.domain.model.authentication.response.VerifyAuthenticationModel
import com.msg.sms.domain.usecase.authentication.FetchAuthenticationFormUseCase
import com.msg.sms.domain.usecase.authentication.SubmitAuthenticationUseCase
import com.msg.sms.domain.usecase.authentication.VerifyAuthenticationUseCase
import com.msg.sms.domain.usecase.fileupload.FileUploadUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val fetchAuthenticationFormUseCase: FetchAuthenticationFormUseCase,
    private val submitAuthenticationUseCase: SubmitAuthenticationUseCase,
    private val verifyAuthenticationUseCase: VerifyAuthenticationUseCase,
    private val fieldUploadUseCase: FileUploadUseCase,
) : ViewModel() {
    private val _authenticationForm: MutableStateFlow<AuthenticationFormModel?> = MutableStateFlow(
        null
    )
    val authenticationForm: StateFlow<AuthenticationFormModel?> get() = _authenticationForm

    private val _verifyAuthenticationData = MutableStateFlow<VerifyAuthenticationModel?>(null)
    val verifyAuthenticationData: StateFlow<VerifyAuthenticationModel?> get() = _verifyAuthenticationData

    private val _fetchAuthenticationStatus = MutableStateFlow<Event<Unit>>(Event.None)

    private val _submitAuthenticationFormStatus = MutableStateFlow<Event<Unit>>(Event.None)
    val submitAuthenticationFormStatus: StateFlow<Event<Unit>> get() = _submitAuthenticationFormStatus

    private val _verifyAuthenticationStatus = MutableStateFlow<Event<Unit>>(Event.None)
    val verifyAuthenticationStatus: StateFlow<Event<Unit>> get() = _verifyAuthenticationStatus

    init {
        verifyAuthentication()
    }

    private fun verifyAuthentication() = viewModelScope.launch {
        runCatching {
            _verifyAuthenticationStatus.value = Event.Loading
            verifyAuthenticationUseCase()
        }.onSuccess {
            it.catch { remoteError ->
                _verifyAuthenticationStatus.value = remoteError.errorHandling()
            }.collect {
                _verifyAuthenticationData.value = it
                if (it.markingBoardType == MarkingBoardType.NOT_SUBMITTED) {
                    fetchAuthentication()
                }
                _verifyAuthenticationStatus.value = Event.Success(Unit)
            }
        }.onFailure {
            _verifyAuthenticationStatus.value = it.errorHandling()
        }
    }

    private fun fetchAuthentication() = viewModelScope.launch {
        runCatching {
            _fetchAuthenticationStatus.value = Event.Loading
            fetchAuthenticationFormUseCase()
        }.onSuccess {
            it.catch { remoteError ->
                _fetchAuthenticationStatus.value = remoteError.errorHandling()
            }.collect {
                _authenticationForm.value = it
                _fetchAuthenticationStatus.value = Event.Success(Unit)
            }
        }.onFailure {
            _fetchAuthenticationStatus.value = it.errorHandling()
        }
    }

    private fun convertAtomicDataListToADataList(atomicDataList: List<AtomicAuthenticationFieldModel>): List<SubmitAuthenticationFormModel> {
        val sectionMap =
            mutableMapOf<String, MutableMap<String, MutableList<AuthenticationFieldModel>>>()

        atomicDataList.forEach { atomicData ->
            val fieldData = AuthenticationFieldModel(
                fieldId = atomicData.fieldId,
                fieldType = atomicData.fieldType,
                value = atomicData.value,
                selectId = atomicData.selectId
            )
            val groupMap = sectionMap.getOrPut(atomicData.sectionId) { mutableMapOf() }
            val fieldList = groupMap.getOrPut(atomicData.groupId) { mutableListOf() }
            fieldList.add(fieldData)
        }

        return sectionMap.map { (sectionId, groupMap) ->
            SubmitAuthenticationFormModel(
                sectionId = sectionId,
                objects = groupMap.map { (groupId, fieldList) ->
                    AuthenticationObjectModel(
                        groupId = groupId,
                        fields = fieldList
                    )
                }
            )
        }
    }

    private suspend fun getFileFromUri(context: Context, uri: Uri): File {
        return withContext(Dispatchers.IO) {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val extension =
                MimeTypeMap.getSingleton().getExtensionFromMimeType(context.contentResolver.getType(uri))

            val file = File(context.cacheDir, "${System.currentTimeMillis()}.${extension}")
            val outputStream: OutputStream = FileOutputStream(file)

            inputStream.use { input ->
                outputStream.use { output ->
                    input?.copyTo(output)
                }
            }

            file
        }
    }

    fun submitAuthenticationForm(context: Context, userData: List<AtomicAuthenticationFieldModel>) =
        viewModelScope.launch {
            val userDataMutableList = userData.toMutableList()

            val haveToUploadFiles = userData.filter { it.file != null }
            var theNumberOfUploadedFile = 0
            haveToUploadFiles.forEach { data ->
                runCatching {
                    val file = getFileFromUri(context, uri = data.file!!)
                    fieldUploadUseCase(file = file)
                }.onSuccess {
                    it.catch { remoteError ->
                        _submitAuthenticationFormStatus.value = remoteError.errorHandling()
                    }.collect { response ->
                        val index = userDataMutableList.indexOf(data)
                        userDataMutableList[index] = data.copy(value = response.fileUrl)
                        theNumberOfUploadedFile++
                    }
                }.onFailure {
                    _submitAuthenticationFormStatus.value = it.errorHandling()
                }
            }
            if (haveToUploadFiles.size == theNumberOfUploadedFile) {
                submitAuthenticationFormData(userDataMutableList)
            }
        }

    private fun submitAuthenticationFormData(userData: List<AtomicAuthenticationFieldModel>) =
        viewModelScope.launch {
            runCatching {
                val formData = convertAtomicDataListToADataList(userData)
                _submitAuthenticationFormStatus.value = Event.Loading
                submitAuthenticationUseCase(formData = SubmitAuthenticationModel(contents = formData))
            }.onSuccess {
                it.catch { remoteError ->
                    _submitAuthenticationFormStatus.value = remoteError.errorHandling()
                }.collect {
                    _submitAuthenticationFormStatus.value = Event.Success(Unit)
                }
            }.onFailure {
                _submitAuthenticationFormStatus.value = it.errorHandling()
            }
        }
}