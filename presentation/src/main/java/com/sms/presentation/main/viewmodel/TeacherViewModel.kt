package com.sms.presentation.main.viewmodel

import com.sms.presentation.main.viewmodel.util.Event
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.usecase.teacher.CommonRegistrationUseCase
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val commonRegistrationUseCase: CommonRegistrationUseCase
) : ViewModel() {
    private val _commonResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val commonResponse = _commonResponse.asStateFlow()

    fun common() = viewModelScope.launch {
        commonRegistrationUseCase().onSuccess {
            it.catch {remoteError ->
                _commonResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _commonResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _commonResponse.value = it.errorHandling()
        }
    }
}