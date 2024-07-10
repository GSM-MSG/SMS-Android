package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.authentication.request.AtomicAuthenticationFieldModel
import com.msg.sms.domain.model.authentication.request.AuthenticationFieldModel
import com.msg.sms.domain.model.authentication.request.AuthenticationObjectModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationFormModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel
import com.msg.sms.domain.usecase.authentication.FetchAuthenticationFormUseCase
import com.msg.sms.domain.usecase.authentication.SubmitAuthenticationUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val fetchAuthenticationFormUseCase: FetchAuthenticationFormUseCase,
    private val submitAuthenticationUseCase: SubmitAuthenticationUseCase,
) : ViewModel() {
    private val _authenticationForm: MutableStateFlow<AuthenticationFormModel?> = MutableStateFlow(
        null
    )
    val authenticationForm: StateFlow<AuthenticationFormModel?> get() = _authenticationForm

    private val _fetchAuthenticationStatus = MutableStateFlow<Event<Unit>>(Event.None)
    val fetchAuthenticationStatus: StateFlow<Event<Unit>> get() = _fetchAuthenticationStatus

    private val _submitAuthenticationFormStatus = MutableStateFlow<Event<Unit>>(Event.None)
    val submitAuthenticationFormStatus: StateFlow<Event<Unit>> get() = _submitAuthenticationFormStatus

    init {
        fetchAuthentication()
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
        // Nested maps to store the structure
        val sectionMap =
            mutableMapOf<String, MutableMap<String, MutableList<AuthenticationFieldModel>>>()

        atomicDataList.forEach { atomicData ->
            val fieldData = AuthenticationFieldModel(
                fieldId = atomicData.fieldId,
                fieldType = atomicData.fieldType,
                value = atomicData.value,
                selectId = atomicData.selectId
            )

            // Get or create the group map for the section
            val groupMap = sectionMap.getOrPut(atomicData.sectionId) { mutableMapOf() }

            // Get or create the field list for the group
            val fieldList = groupMap.getOrPut(atomicData.groupId) { mutableListOf() }

            // Add the field data to the group
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

    fun submitAuthenticationForm(userData: List<AtomicAuthenticationFieldModel>) =
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