package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.authentication.AuthenticationFormModel
import com.msg.sms.domain.usecase.authentication.FetchAuthenticationFormUseCase
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
) : ViewModel() {
    private val _authenticationForm = MutableStateFlow(
        AuthenticationFormModel(listOf())
    )
    val authenticationForm: StateFlow<AuthenticationFormModel> get() = _authenticationForm

    private val _fetchAuthenticationStatus = MutableStateFlow<Event<Unit>>(Event.None)
    val fetchAuthenticationStatus: StateFlow<Event<Unit>> get() = _fetchAuthenticationStatus

    init {
        fetchAuthentication()
    }

    fun fetchAuthentication() = viewModelScope.launch {
        runCatching {
            _fetchAuthenticationStatus.emit(Event.Loading)
            fetchAuthenticationFormUseCase()
        }.onSuccess {
            it.catch { remoteError ->
                _fetchAuthenticationStatus.value = remoteError.errorHandling()
            }.collect {
                _authenticationForm.value = it
                _fetchAuthenticationStatus.emit(Event.Success(Unit))
            }
        }.onFailure {
            _fetchAuthenticationStatus.value = it.errorHandling()
        }
    }
}