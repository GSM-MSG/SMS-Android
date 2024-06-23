package com.sms.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                Log.d("TTTTTTTTTTE", remoteError.toString())
            }.collect {
                _authenticationForm.value = it
                _fetchAuthenticationStatus.value = Event.Success(Unit)
                Log.d("TTTTTTTTTT", it.toString())
            }
        }.onFailure {
            _fetchAuthenticationStatus.value = it.errorHandling()
            Log.d("TTTTTTTTTTEE", it.toString())
        }
    }

    fun submitAuthenticationForm(formDate: SubmitAuthenticationModel) = viewModelScope.launch {
        runCatching {
            _submitAuthenticationFormStatus.value = Event.Loading
            submitAuthenticationUseCase(formDate)
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