package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.student.response.GetStudentForAnonymous
import com.msg.sms.domain.model.student.response.GetStudentForStudent
import com.msg.sms.domain.model.student.response.GetStudentForTeacher
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.usecase.auth.DeleteTokenUseCase
import com.msg.sms.domain.usecase.auth.LogoutUseCase
import com.msg.sms.domain.usecase.auth.WithdrawalUseCase
import com.msg.sms.domain.usecase.student.GetStudentDetailForStudentUseCase
import com.msg.sms.domain.usecase.student.GetStudentListUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForAnonymousUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForTeacherUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val getStudentDetailForTeacherUseCase: GetUserDetailForTeacherUseCase,
    private val getStudentDetailForStudentUseCase: GetStudentDetailForStudentUseCase,
    private val getStudentDetailForAnonymousUseCase: GetUserDetailForAnonymousUseCase
) : ViewModel() {
    private val _getStudentListResponse = MutableStateFlow<Event<StudentListModel>>(Event.Loading)
    val getStudentListResponse = _getStudentListResponse.asStateFlow()

    private val _logoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val logoutResponse = _logoutResponse.asStateFlow()

    private val _withdrawalResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val withdrawalResponse = _withdrawalResponse.asStateFlow()

    private val _getStudentDetailForTeacherResponse =
        MutableStateFlow<Event<GetStudentForTeacher>>(Event.Loading)
    val getStudentDetailForTeacherResponse = _getStudentDetailForTeacherResponse.asStateFlow()

    private val _getStudentDetailForStudentResponse =
        MutableStateFlow<Event<GetStudentForStudent>>(Event.Loading)
    val getStudentDetailForStudentResponse = _getStudentDetailForStudentResponse.asStateFlow()

    private val _getStudentDetailForAnonymousResponse =
        MutableStateFlow<Event<GetStudentForAnonymous>>(Event.Loading)
    val getStudentDetailForAnonymousResponse = _getStudentDetailForAnonymousResponse.asStateFlow()

    fun getStudentListRequest(
        page: Int,
        size: Int,
        majors: List<String>? = null,
        techStacks: List<String>? = null,
        grade: Int? = null,
        classNum: Int? = null,
        department: List<String>? = null,
        stuNumSort: String? = null,
        formOfEmployment: String? = null,
        minGsmAuthenticationScore: Int? = null,
        maxGsmAuthenticationScore: Int? = null,
        minSalary: Int? = null,
        maxSalary: Int? = null,
        gsmAuthenticationScoreSort: String? = null,
        salarySort: String? = null,
    ) = viewModelScope.launch {
        _getStudentListResponse.value = Event.Loading
        getStudentListUseCase(
            page = page,
            size = size,
            majors = majors,
            techStacks = techStacks,
            grade = grade,
            classNum = classNum,
            department = department,
            stuNumSort = stuNumSort,
            formOfEmployment = formOfEmployment,
            minGsmAuthenticationScore = minGsmAuthenticationScore,
            maxGsmAuthenticationScore = maxGsmAuthenticationScore,
            minSalary = minSalary,
            maxSalary = maxSalary,
            gsmAuthenticationScoreSort = gsmAuthenticationScoreSort,
            salarySort = salarySort
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentListResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentListResponse.value = error.errorHandling()
        }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase().onSuccess {
            it.catch { remoteError ->
                _logoutResponse.value = remoteError.errorHandling()
            }.collect { response ->
                deleteTokenUseCase()
                _logoutResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _logoutResponse.value = it.errorHandling()
        }
    }

    fun withdrawal() = viewModelScope.launch {
        withdrawalUseCase().onSuccess {
            it.catch { remoteError ->
                _withdrawalResponse.value = remoteError.errorHandling()
            }.collect { response ->
                deleteTokenUseCase()
                _withdrawalResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _withdrawalResponse.value = it.errorHandling()
        }
    }

    fun getStudentDetailForTeacher(uuid: UUID) = viewModelScope.launch {
        getStudentDetailForTeacherUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailForTeacherResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailForTeacherResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentDetailForTeacherResponse.value = error.errorHandling()
        }
    }

    fun getStudentDetailForStudent(uuid: UUID) = viewModelScope.launch {
        getStudentDetailForStudentUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailForStudentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailForStudentResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentDetailForStudentResponse.value = error.errorHandling()
        }
    }

    fun getStudentDetailForAnonymous(uuid: UUID) = viewModelScope.launch {
        getStudentDetailForAnonymousUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailForAnonymousResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailForAnonymousResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentDetailForAnonymousResponse.value = error.errorHandling()
        }
    }
}