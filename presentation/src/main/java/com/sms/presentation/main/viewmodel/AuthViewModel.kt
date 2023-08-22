package com.sms.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.AccessValidationResponseModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.usecase.auth.*
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val gAuthLoginUseCase: GAuthLoginUseCase,
    private val saveTheLoginDataUseCase: SaveTheLoginDataUseCase,
    private val getMajorListUseCase: GetMajorListUseCase,
    private val accessValidationUseCase: AccessValidationUseCase,
    private val getRoleInfoUseCase: GetRoleInfoUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
) : ViewModel() {
    private val _gAuthLoginRequest = MutableLiveData<Event<GAuthLoginResponseModel>>()
    val gAuthLoginRequest: LiveData<Event<GAuthLoginResponseModel>> get() = _gAuthLoginRequest

    private val _getMajorList = MutableLiveData<Event<MajorListModel>>()
    val getMajorList: LiveData<Event<MajorListModel>> get() = _getMajorList

    private val _saveTokenRequest = MutableLiveData<Event<MajorListModel>>()
    val saveTokenRequest: LiveData<Event<MajorListModel>> get() = _saveTokenRequest

    private val _accessValidationResponse =
        MutableStateFlow<Event<AccessValidationResponseModel>>(Event.Loading)
    val accessValidationResponse = _accessValidationResponse.asStateFlow()

    private val _saveRoleResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val saveRoleResponse = _saveRoleResponse.asStateFlow()

    private val _getRoleResponse = MutableStateFlow<Event<String>>(Event.Loading)
    val getRoleResponse = _getRoleResponse.asStateFlow()

    fun gAuthLogin(code: String) = viewModelScope.launch {
        gAuthLoginUseCase(
            GAuthLoginRequestModel(code = code)
        ).onSuccess {
            it.catch { remoteError ->
                _gAuthLoginRequest.value = remoteError.errorHandling()
            }.collect { response ->
                _gAuthLoginRequest.value = Event.Success(data = response)
            }
        }.onFailure {
            _gAuthLoginRequest.value = it.errorHandling()
        }
    }

    fun getMajorList() = viewModelScope.launch {
        getMajorListUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getMajorList.value = remoteError.errorHandling()
                }.collect { response ->
                    _getMajorList.value = Event.Success(data = response)
                }
            }.onFailure {
                _getMajorList.value = it.errorHandling()
            }
    }

    fun saveTheLoginData(data: GAuthLoginResponseModel) = viewModelScope.launch {
        saveTheLoginDataUseCase(
            data = data
        ).onSuccess {
            _saveTokenRequest.value = Event.Success()
        }.onFailure {
            _saveTokenRequest.value = it.errorHandling()
        }
    }

    fun accessValidation() = viewModelScope.launch {
        accessValidationUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _accessValidationResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _accessValidationResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _accessValidationResponse.value = error.errorHandling()
            }
    }

    fun getRoleInfo() = viewModelScope.launch {
        getRoleInfoUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getRoleResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getRoleResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getRoleResponse.value = error.errorHandling()
            }
    }

    fun deleteToken() = viewModelScope.launch {
        deleteTokenUseCase()
    }
}