package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.user.response.MyProfileModel
import com.msg.sms.domain.usecase.user.GetMyProfileUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase,
) : ViewModel() {
    private val _getProfileResponse = MutableStateFlow<Event<MyProfileModel>>(Event.Loading)
    val getProfileResponse = _getProfileResponse.asStateFlow()

    fun getMyProfile() = viewModelScope.launch {
        getMyProfileUseCase().onSuccess {
            it.catch { remoteError ->
                _getProfileResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getProfileResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _getProfileResponse.value = it.errorHandling()
        }
    }
}