package com.sms.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sms.presentation.main.viewmodel.util.Event
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msg.sms.domain.model.teacher.request.HomeroomTeacherRequestModel
import com.msg.sms.domain.usecase.teacher.CommonRegistrationUseCase
import com.msg.sms.domain.usecase.teacher.HeadOfDepartmentRegistrationUseCase
import com.msg.sms.domain.usecase.teacher.HomeroomRegistrationUseCase
import com.msg.sms.domain.usecase.teacher.PrincipalRegistrationUseCase
import com.msg.sms.domain.usecase.teacher.VicePrincipalRegistrationUseCase
import com.sms.presentation.main.ui.teacher_registration.state.Position
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
    private val commonRegistrationUseCase: CommonRegistrationUseCase,
    private val principalRegistrationUseCase: PrincipalRegistrationUseCase,
    private val vicePrincipalRegistrationUseCase: VicePrincipalRegistrationUseCase,
    private val headOfDepartmentRegistrationUseCase: HeadOfDepartmentRegistrationUseCase,
    private val homeroomRegistrationUseCase: HomeroomRegistrationUseCase
) : ViewModel() {
    private val _commonResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val commonResponse = _commonResponse.asStateFlow()

    private val _principalResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val principalResponse = _principalResponse.asStateFlow()

    private val _vicePrincipalResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val vicePrincipalResponse = _vicePrincipalResponse.asStateFlow()

    private val _headOfDepartmentResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val headOfDepartmentResponse = _headOfDepartmentResponse.asStateFlow()

    private val _homeroomResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val homeroomResponse = _homeroomResponse.asStateFlow()

    private var _positionData = MutableStateFlow("")
    val positionData = _positionData.asStateFlow()

    private val _gradeData = MutableStateFlow("")
    val gradeData = _gradeData.asStateFlow()

    private val _classData = MutableStateFlow("")
    val classData = _classData.asStateFlow()

    fun selectedPositionDataChange(positionData: String) {
        _positionData.value = positionData
    }
    fun selectedGradeDataChange(gradeData: String) {
        _gradeData.value = gradeData
    }
    fun selectedClassDataChange(classData: String) {
        _classData.value = classData
    }
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

    fun principal() = viewModelScope.launch {
        principalRegistrationUseCase().onSuccess {
            it.catch {remoteError ->
                _principalResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _principalResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _principalResponse.value = it.errorHandling()
        }
    }

    fun vicePrincipal() = viewModelScope.launch {
        vicePrincipalRegistrationUseCase().onSuccess {
            it.catch {remoteError ->
                _principalResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _vicePrincipalResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _vicePrincipalResponse.value = it.errorHandling()
        }
    }

    fun headOfDepartment() = viewModelScope.launch {
        headOfDepartmentRegistrationUseCase().onSuccess {
            it.catch {remoteError ->
                _headOfDepartmentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _headOfDepartmentResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _headOfDepartmentResponse.value = it.errorHandling()
        }
    }

    fun homeroom(grade: Int, classNum: Int) = viewModelScope.launch {
        homeroomRegistrationUseCase(
            HomeroomTeacherRequestModel(
                grade = grade,
                classNum = classNum
            )
        ).onSuccess {
            it.catch { remoteError ->
                _headOfDepartmentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _homeroomResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _homeroomResponse.value = it.errorHandling()
        }
    }
}