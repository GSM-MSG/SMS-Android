package com.sms.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.usecase.GAuthLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val gAuthLoginUseCase: GAuthLoginUseCase
) : ViewModel() {
    fun gAuthLogin(code: String) = viewModelScope.launch {
        gAuthLoginUseCase(
            GAuthLoginRequestModel(code = code)
        ).onSuccess {
            Log.d("LoginResponse", it.toString())
        }.onFailure {
            Log.d("LoginResponse", it.toString())
        }
    }
}