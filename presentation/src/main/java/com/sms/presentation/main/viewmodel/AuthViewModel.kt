package com.sms.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.usecase.auth.GAuthLoginUseCase
import com.msg.sms.domain.usecase.auth.SaveTheLoginDataUseCase
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val gAuthLoginUseCase: GAuthLoginUseCase,
    private val saveTheLoginDataUseCase: SaveTheLoginDataUseCase,
    private val getMajorListUseCase: GetMajorListUseCase,
) : ViewModel() {
    private val _gAuthLoginRequest = MutableLiveData<Event<GAuthLoginResponseModel>>()
    val gAuthLoginRequest: LiveData<Event<GAuthLoginResponseModel>> get() = _gAuthLoginRequest

    private val _getMajorList = MutableLiveData<Event<MajorListModel>>()
    val getMajorList: LiveData<Event<MajorListModel>> get() = _getMajorList

    fun gAuthLogin(code: String) = viewModelScope.launch {
        gAuthLoginUseCase(
            GAuthLoginRequestModel(code = code)
        ).onSuccess {
            _gAuthLoginRequest.value = Event.Success(data = it)
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
        ).onFailure {
            Log.d("TAG", "saveTheLoginData: $it")
        }
    }
}