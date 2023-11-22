package com.sms.presentation.main.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.student.request.ProjectModel
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
import com.sms.presentation.main.ui.fill_out_information.data.*
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class FillOutViewModel @Inject constructor(
    private val enterStudentInformationUseCase: EnterStudentInformationUseCase,
    private val getMajorListUseCase: GetMajorListUseCase,
    private val imageUploadUseCase: ImageUploadUseCase,
) : ViewModel() {
    private val _enterInformationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val enterInformationResponse = _enterInformationResponse.asStateFlow()

    private val _getMajorListResponse = MutableStateFlow<Event<MajorListModel>>(Event.Loading)
    val getMajorListResponse = _getMajorListResponse.asStateFlow()

    private val _profileImageUploadResponse = MutableStateFlow<Event<String>>(Event.Loading)
    val profileImageUploadResponse = _profileImageUploadResponse.asStateFlow()

    private val major = mutableStateOf("")
    private val enteredMajor = mutableStateOf("")
    private val techStacks = mutableStateListOf<String>()
    private val profileImageUri = mutableStateOf(Uri.EMPTY)
    private val introduce = mutableStateOf("")
    private val contactEmail = mutableStateOf("")

    fun getEnteredProfileInformation(): ProfileData {
        return ProfileData(
            profileImageUri = profileImageUri.value,
            introduce = introduce.value,
            contactEmail = contactEmail.value,
            enteredMajor = enteredMajor.value,
            major = major.value,
            techStack = techStacks
        )
    }

    fun setEnteredProfileInformation(
        enteredMajor: String,
        major: String,
        techStack: List<String>,
        profileImgUri: Uri,
        introduce: String,
        contactEmail: String,
    ) {
        this.enteredMajor.value = enteredMajor
        this.major.value = major
        this.techStacks.removeAll { !techStack.contains(it) }
        this.techStacks.addAll(techStack.filter { !this.techStacks.contains(it) })
        this.profileImageUri.value = profileImgUri
        this.introduce.value = introduce
        this.contactEmail.value = contactEmail
    }

    fun getMajorList() {
        viewModelScope.launch {
            getMajorListUseCase().onSuccess {
                it.catch { remoteError ->
                    _getMajorListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getMajorListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getMajorListResponse.value = it.errorHandling()
            }
        }
    }

    fun enterStudentInformation(
        major: String,
        techStack: List<String>,
        profileImgUrl: String,
        introduce: String,
        contactEmail: String,
    ) = viewModelScope.launch {
        enterStudentInformationUseCase(
            EnterStudentInformationModel(
                major = major,
                techStacks = techStack,
                profileImgUrl = profileImgUrl,
                introduce = introduce,
                contactEmail = contactEmail
            )
        ).onSuccess {
            it.catch { remoteError ->
                _enterInformationResponse.value = remoteError.errorHandling()
            }.collect {
                _enterInformationResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _enterInformationResponse.value = error.errorHandling()
        }
    }

    fun profileImageUploadAsync(profileImage: Uri, context: Context) = viewModelScope.async {
        imageUploadUseCase(
            file = profileImage.toMultipartBody(context)!!
        ).onSuccess {
            it.catch { remoteError ->
                _profileImageUploadResponse.value = remoteError.errorHandling()
                this@async.cancel()
            }.collect { response ->
                _profileImageUploadResponse.value = Event.Success(data = response.fileUrl)
            }
        }.onFailure { error ->
            _profileImageUploadResponse.value = error.errorHandling()
            this@async.cancel()
        }
    }
}