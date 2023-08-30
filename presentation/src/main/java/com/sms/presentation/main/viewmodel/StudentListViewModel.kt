package com.sms.presentation.main.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.student.response.GetStudentForAnonymousModel
import com.msg.sms.domain.model.student.response.GetStudentForStudentModel
import com.msg.sms.domain.model.student.response.GetStudentForTeacherModel
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.model.user.response.ProfileImageModel
import com.msg.sms.domain.usecase.auth.DeleteTokenUseCase
import com.msg.sms.domain.usecase.auth.LogoutUseCase
import com.msg.sms.domain.usecase.auth.WithdrawalUseCase
import com.msg.sms.domain.usecase.student.GetStudentDetailForStudentUseCase
import com.msg.sms.domain.usecase.student.GetStudentListUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForAnonymousUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForTeacherUseCase
import com.msg.sms.domain.usecase.user.GetProfileImageUseCase
import com.sms.presentation.main.ui.filter.data.FilterClass.*
import com.sms.presentation.main.ui.filter.data.FilterDepartment.*
import com.sms.presentation.main.ui.filter.data.FilterGrade.*
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment.*
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
    private val getStudentDetailForAnonymousUseCase: GetUserDetailForAnonymousUseCase,
    private val getProfileImageUseCase: GetProfileImageUseCase
) : ViewModel() {
    private val _getStudentListResponse = MutableStateFlow<Event<StudentListModel>>(Event.Loading)
    val getStudentListResponse = _getStudentListResponse.asStateFlow()

    private val _logoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val logoutResponse = _logoutResponse.asStateFlow()

    private val _withdrawalResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val withdrawalResponse = _withdrawalResponse.asStateFlow()

    private val _getStudentDetailForTeacherResponse =
        MutableStateFlow<Event<GetStudentForTeacherModel>>(Event.Loading)
    val getStudentDetailForTeacherResponse = _getStudentDetailForTeacherResponse.asStateFlow()

    private val _getStudentDetailForStudentResponse =
        MutableStateFlow<Event<GetStudentForStudentModel>>(Event.Loading)
    val getStudentDetailForStudentResponse = _getStudentDetailForStudentResponse.asStateFlow()

    private val _getStudentDetailForAnonymousResponse =
        MutableStateFlow<Event<GetStudentForAnonymousModel>>(Event.Loading)
    val getStudentDetailForAnonymousResponse = _getStudentDetailForAnonymousResponse.asStateFlow()

    private val _getStudentProfileImageResponse =
        MutableStateFlow<Event<ProfileImageModel>>(Event.Loading)
    val getStudentProfileImageResponse = _getStudentProfileImageResponse.asStateFlow()

    var majorList = listOf<String>()
    val gradeList = listOf(FIRST_GRADE, SECOND_GRADE, THIRD_GRADE)
    val classList = listOf(FIRST, SECOND, THIRD, FOURTH)
    val departmentList = listOf(SW_DEVELOPMENT, SMART_IOT_DEVELOPMENT, AI_DEVELOPMENT)
    val typeOfEmploymentList = listOf(FULL_TIME, TEMPORARY, CONTRACT, INTERN)
    var selectedMajorList = mutableStateListOf<String>()
    var selectedGradeList = mutableStateListOf<Int>()
    var selectedClassList = mutableStateListOf<Int>()
    var selectedDepartmentList = mutableStateListOf<String>()
    var selectedTypeOfEmploymentList = mutableStateListOf<String>()
    var gsmScoreSliderValues = mutableStateOf(0f..990f)
    var desiredAnnualSalarySliderValues = mutableStateOf(0f..9999f)
    var isSchoolNumberAscendingOrder = mutableStateOf(true)
    var isGsmScoreAscendingOrder = mutableStateOf(true)
    var isDesiredAnnualSalaryAscendingOrder = mutableStateOf(true)
    var detailStackList = mutableStateOf("")

    fun getStudentListRequest(
        page: Int,
        size: Int
    ) = viewModelScope.launch {
        _getStudentListResponse.value = Event.Loading
        getStudentListUseCase(
            page = page,
            size = size,
            majors = selectedMajorList.ifEmpty { null },
            techStacks = if (detailStackList.value.isNotBlank()) detailStackList.value.split(",") else null,
            grade = selectedGradeList.ifEmpty { null },
            classNum = selectedClassList.ifEmpty { null },
            department = selectedDepartmentList.ifEmpty { null },
            stuNumSort = if (isSchoolNumberAscendingOrder.value) "ASCENDING" else "DESCENDING",
            formOfEmployment = selectedTypeOfEmploymentList.ifEmpty { null },
            minGsmAuthenticationScore = gsmScoreSliderValues.value.start.toInt(),
            maxGsmAuthenticationScore = gsmScoreSliderValues.value.endInclusive.toInt(),
            minSalary = desiredAnnualSalarySliderValues.value.start.toInt(),
            maxSalary = desiredAnnualSalarySliderValues.value.endInclusive.toInt(),
            gsmAuthenticationScoreSort = if (isGsmScoreAscendingOrder.value) "ASCENDING" else "DESCENDING",
            salarySort = if (isDesiredAnnualSalaryAscendingOrder.value) "ASCENDING" else "DESCENDING"
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
        _getStudentDetailForTeacherResponse.value = Event.Loading
        getStudentDetailForTeacherUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailForTeacherResponse.value = remoteError.errorHandling()
            }.collect { response ->
                Log.d("dddd", response.toString())
                _getStudentDetailForTeacherResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentDetailForTeacherResponse.value = error.errorHandling()
        }
    }

    fun getStudentDetailForStudent(uuid: UUID) = viewModelScope.launch {
        _getStudentDetailForStudentResponse.value = Event.Loading
        getStudentDetailForStudentUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailForStudentResponse.value = remoteError.errorHandling()
            }.collect { response ->
                Log.d("dddd", response.toString())
                _getStudentDetailForStudentResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentDetailForStudentResponse.value = error.errorHandling()
        }
    }

    fun getStudentDetailForAnonymous(uuid: UUID) = viewModelScope.launch {
        _getStudentDetailForAnonymousResponse.value = Event.Loading
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

    fun getProfileImageUrl() = viewModelScope.launch {
        _getStudentProfileImageResponse.value = Event.Loading
        getProfileImageUseCase().onSuccess {
            it.catch { remoteError ->
                _getStudentProfileImageResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentProfileImageResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _getStudentProfileImageResponse.value = error.errorHandling()
        }
    }

    fun resetFilter() {
        selectedMajorList.clear()
        selectedGradeList.clear()
        selectedClassList.clear()
        selectedDepartmentList.clear()
        selectedTypeOfEmploymentList.clear()
        detailStackList.value = ""
        isSchoolNumberAscendingOrder.value = true
        isGsmScoreAscendingOrder.value = true
        isDesiredAnnualSalaryAscendingOrder.value = true
    }
}