package com.sms.presentation.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.student.request.CreateInformationLinkRequestModel
import com.msg.sms.domain.model.student.response.CreateInformationLinkResponseModel
import com.msg.sms.domain.model.student.response.GetStudentModel
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.model.student.response.StudentModel
import com.msg.sms.domain.model.user.response.ProfileImageModel
import com.msg.sms.domain.usecase.auth.DeleteTokenUseCase
import com.msg.sms.domain.usecase.auth.LogoutUseCase
import com.msg.sms.domain.usecase.auth.WithdrawalUseCase
import com.msg.sms.domain.usecase.student.CreateInformationLinkUseCase
import com.msg.sms.domain.usecase.student.GetStudentDetailForStudentUseCase
import com.msg.sms.domain.usecase.student.GetStudentListUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForAnonymousUseCase
import com.msg.sms.domain.usecase.student.GetUserDetailForTeacherUseCase
import com.msg.sms.domain.usecase.user.GetProfileImageUseCase
import com.sms.presentation.main.ui.filter.data.FilterClass
import com.sms.presentation.main.ui.filter.data.FilterClass.FIRST
import com.sms.presentation.main.ui.filter.data.FilterClass.FOURTH
import com.sms.presentation.main.ui.filter.data.FilterClass.SECOND
import com.sms.presentation.main.ui.filter.data.FilterClass.THIRD
import com.sms.presentation.main.ui.filter.data.FilterDepartment
import com.sms.presentation.main.ui.filter.data.FilterDepartment.AI_DEVELOPMENT
import com.sms.presentation.main.ui.filter.data.FilterDepartment.SMART_IOT_DEVELOPMENT
import com.sms.presentation.main.ui.filter.data.FilterDepartment.SW_DEVELOPMENT
import com.sms.presentation.main.ui.filter.data.FilterGrade
import com.sms.presentation.main.ui.filter.data.FilterGrade.FIRST_GRADE
import com.sms.presentation.main.ui.filter.data.FilterGrade.SECOND_GRADE
import com.sms.presentation.main.ui.filter.data.FilterGrade.THIRD_GRADE
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment.CONTRACT
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment.FULL_TIME
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment.INTERN
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment.TEMPORARY
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID
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
    private val getProfileImageUseCase: GetProfileImageUseCase,
    private val createInformationLinkUseCase: CreateInformationLinkUseCase
) : ViewModel() {
    private val _getStudentListResponse = MutableStateFlow<Event<StudentListModel>>(Event.Loading)
    val getStudentListResponse = _getStudentListResponse.asStateFlow()

    private val _logoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val logoutResponse = _logoutResponse.asStateFlow()

    private val _withdrawalResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val withdrawalResponse = _withdrawalResponse.asStateFlow()

    private val _getStudentDetailStatusResponse =
        MutableStateFlow<Event<Unit>>(Event.Loading)
    val getStudentDetailStatusResponse = _getStudentDetailStatusResponse.asStateFlow()

    private val _getStudentDetailResponse = MutableStateFlow(GetStudentModel())
    val getStudentDetailResponse = _getStudentDetailResponse.asStateFlow()

    private val _getStudentProfileImageResponse =
        MutableStateFlow<Event<ProfileImageModel>>(Event.Loading)
    val getStudentProfileImageResponse = _getStudentProfileImageResponse.asStateFlow()

    private val _createInformationLinkStatusResponse = MutableStateFlow<Event<CreateInformationLinkResponseModel>>(Event.Loading)
    val createInformationLinkStatusResponse = _createInformationLinkStatusResponse.asStateFlow()

    private val _createInformationLinkResponse = MutableStateFlow("")
    val createInformationLinkResponse = _createInformationLinkResponse.asStateFlow()

    // Main List
    var studentList = mutableStateListOf<StudentModel>()
        private set

    //Filter - Selector
    var majorList = listOf<String>()
    val gradeList = listOf(FIRST_GRADE, SECOND_GRADE, THIRD_GRADE)
    val classList = listOf(FIRST, SECOND, THIRD, FOURTH)
    val departmentList = listOf(SW_DEVELOPMENT, SMART_IOT_DEVELOPMENT, AI_DEVELOPMENT)
    val typeOfEmploymentList = listOf(FULL_TIME, TEMPORARY, CONTRACT, INTERN)

    var filterMajorList = mutableStateListOf<String>()
        private set
    var filterGradeList = mutableStateListOf<FilterGrade>()
        private set
    var filterClassList = mutableStateListOf<FilterClass>()
        private set
    var filterDepartmentList = mutableStateListOf<FilterDepartment>()
        private set
    var filterTypeOfEmploymentList = mutableStateListOf<FilterTypeOfEmployment>()
        private set

    var selectedMajorList = mutableStateListOf<String>()
        private set
    var selectedGradeList = mutableStateListOf<FilterGrade>()
        private set
    var selectedClassList = mutableStateListOf<FilterClass>()
        private set
    var selectedDepartmentList = mutableStateListOf<FilterDepartment>()
        private set
    var selectedTypeOfEmploymentList = mutableStateListOf<FilterTypeOfEmployment>()
        private set
    //

    //Filter - Slider
    var filterGsmScoreSliderValues = mutableStateOf(0f..990f)
        private set
    var filterDesiredAnnualSalarySliderValues = mutableStateOf(0f..9999f)
        private set

    var selectedGsmScoreSliderValues = mutableStateOf(0f..990f)
        private set
    var selectedDesiredAnnualSalarySliderValues = mutableStateOf(0f..9999f)
        private set
    //

    //Filter - SelectionControl
    var filterSchoolNumberAscendingOrder = mutableStateOf(true)
        private set
    var filterGsmScoreAscendingOrder = mutableStateOf(true)
        private set
    var filterDesiredAnnualSalaryAscendingOrder = mutableStateOf(true)
        private set

    var selectedSchoolNumberAscendingOrder = mutableStateOf(true)
        private set
    var selectedGsmScoreAscendingOrder = mutableStateOf(true)
        private set
    var selectedDesiredAnnualSalaryAscendingOrder = mutableStateOf(true)
        private set
    //

    //Filter - DetailStack
    var filterDetailStackList = mutableStateListOf<String>()
        private set
    var selectedDetailStack = mutableStateListOf<String>()
        private set

    private val _selectedExpirationDaysData = MutableStateFlow("")
    val selectedExpirationDaysData = _selectedExpirationDaysData.asStateFlow()

    private val _studentId = MutableLiveData<String>()
    val studentId: LiveData<String> get() = _studentId

    fun saveStudentId(id: UUID) = viewModelScope.launch{
        _studentId.value = id.toString()
    }

    private val _createInformationLinkState = MutableStateFlow(false)
    val createInformationLinkState = _createInformationLinkState.asStateFlow()

    fun saveCreateInformationLinkState(boolean: Boolean) {
        _createInformationLinkState.value = boolean
    }

    fun selectedExpirationDaysDataChange(expirationDays: String) {
        _selectedExpirationDaysData.value = expirationDays
    }

    init {
        getStudentListRequest(1, 20)
    }

    fun getStudentListRequest(
        page: Int,
        size: Int,
    ) = viewModelScope.launch {
        _getStudentListResponse.value = Event.Loading
        getStudentListUseCase(
            page = page,
            size = size,
            majors = filterMajorList.ifEmpty { null },
            techStacks = filterDetailStackList.ifEmpty { null },
            grade = filterGradeList.map { it.enum }.ifEmpty { null },
            classNum = filterClassList.map { it.enum }.ifEmpty { null },
            department = filterDepartmentList.map { it.enum }.ifEmpty { null },
            stuNumSort = if (filterSchoolNumberAscendingOrder.value) "ASCENDING" else "DESCENDING",
            formOfEmployment = filterTypeOfEmploymentList.map { it.enum }.ifEmpty { null },
            minGsmAuthenticationScore = filterGsmScoreSliderValues.value.start.toInt()
                .takeIf { it != 0 },
            maxGsmAuthenticationScore = filterGsmScoreSliderValues.value.endInclusive.toInt()
                .takeIf { it != 990 },
            minSalary = filterDesiredAnnualSalarySliderValues.value.start.toInt()
                .takeIf { it != 0 },
            maxSalary = filterDesiredAnnualSalarySliderValues.value.endInclusive.toInt()
                .takeIf { it != 9999 },
            gsmAuthenticationScoreSort = if (filterGsmScoreAscendingOrder.value) "ASCENDING" else "DESCENDING",
            salarySort = if (filterDesiredAnnualSalaryAscendingOrder.value) "ASCENDING" else "DESCENDING"
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentListResponse.value = remoteError.errorHandling()
            }.collect { response ->
                studentList.addAll(response.content)
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
        _getStudentDetailResponse.value = GetStudentModel()
        _getStudentDetailStatusResponse.value = Event.Loading
        getStudentDetailForTeacherUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailStatusResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailStatusResponse.value = Event.Success(data = Unit)
                _getStudentDetailResponse.value = response
            }
        }.onFailure {
            _getStudentDetailStatusResponse.value = it.errorHandling()
        }
    }

    fun getStudentDetailForStudent(uuid: UUID) = viewModelScope.launch {
        _getStudentDetailResponse.value = GetStudentModel()
        _getStudentDetailStatusResponse.value = Event.Loading
        getStudentDetailForStudentUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailStatusResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailStatusResponse.value = Event.Success(data = Unit)
                _getStudentDetailResponse.value = response
            }
        }.onFailure {
            _getStudentDetailStatusResponse.value = it.errorHandling()
        }
    }

    fun getStudentDetailForAnonymous(uuid: UUID) = viewModelScope.launch {
        _getStudentDetailResponse.value = GetStudentModel()
        _getStudentDetailStatusResponse.value = Event.Loading
        getStudentDetailForAnonymousUseCase(
            uuid = uuid
        ).onSuccess {
            it.catch { remoteError ->
                _getStudentDetailStatusResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getStudentDetailStatusResponse.value = Event.Success(data = Unit)
                _getStudentDetailResponse.value = response
            }
        }.onFailure {
            _getStudentDetailStatusResponse.value = it.errorHandling()
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

    fun createInformationLink(studentId: String, periodDay: Long) = viewModelScope.launch {
        _createInformationLinkStatusResponse.value = Event.Loading
        createInformationLinkUseCase(
            CreateInformationLinkRequestModel(
                studentId = studentId,
                periodDay = periodDay
            )
        ).onSuccess {
            it.catch { remoteError ->
                _createInformationLinkStatusResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _createInformationLinkStatusResponse.value = Event.Success(data = response)
                _createInformationLinkResponse.value = response.token
            }
        }.onFailure { error ->
            _createInformationLinkStatusResponse.value = error.errorHandling()
        }
    }

    fun clearStudentList() {
        studentList.clear()
    }

    //Filter - Selector Setter (start)
    fun setFilterGradeList(gradeList: List<FilterGrade>) {
        filterGradeList.removeAll(filterGradeList.filter {
            !gradeList.contains(it)
        })
        filterGradeList.addAll(gradeList.filter {
            !filterGradeList.contains(it)
        })
    }

    fun setFilterClassList(classList: List<FilterClass>) {
        filterClassList.removeAll(filterClassList.filter {
            !classList.contains(it)
        })
        filterClassList.addAll(classList.filter {
            !filterClassList.contains(it)
        })
    }

    fun setFilterDepartmentList(departmentList: List<FilterDepartment>) {
        filterDepartmentList.removeAll(filterDepartmentList.filter {
            !departmentList.contains(it)
        })
        filterDepartmentList.addAll(departmentList.filter {
            !filterDepartmentList.contains(it)
        })
    }

    fun setFilterMajorList(majorList: List<String>) {
        filterMajorList.removeAll(filterMajorList.filter {
            !majorList.contains(it)
        })
        filterMajorList.addAll(majorList.filter {
            !filterMajorList.contains(it)
        })
    }

    fun setFilterTypeOfEmploymentList(typeOfEmploymentList: List<FilterTypeOfEmployment>) {
        filterTypeOfEmploymentList.removeAll(filterTypeOfEmploymentList.filter {
            !typeOfEmploymentList.contains(it)
        })
        filterTypeOfEmploymentList.addAll(typeOfEmploymentList.filter {
            !filterTypeOfEmploymentList.contains(it)
        })
    }

    fun setSelectedGradeList(gradeList: List<FilterGrade>) {
        selectedGradeList.removeAll(selectedGradeList.filter {
            !gradeList.contains(it)
        })
        selectedGradeList.addAll(gradeList.filter {
            !selectedGradeList.contains(it)
        })
    }

    fun setSelectedClassList(classList: List<FilterClass>) {
        selectedClassList.removeAll(selectedClassList.filter {
            !classList.contains(it)
        })
        selectedClassList.addAll(classList.filter {
            !selectedClassList.contains(it)
        })
    }

    fun setSelectedDepartmentList(departmentList: List<FilterDepartment>) {
        selectedDepartmentList.removeAll(selectedDepartmentList.filter {
            !departmentList.contains(it)
        })
        selectedDepartmentList.addAll(departmentList.filter {
            !selectedDepartmentList.contains(it)
        })
    }

    fun setSelectedMajorList(majorList: List<String>) {
        selectedMajorList.removeAll(selectedMajorList.filter {
            !majorList.contains(it)
        })
        selectedMajorList.addAll(majorList.filter {
            !selectedMajorList.contains(it)
        })
    }

    fun setSelectedTypeOfEmploymentList(typeOfEmploymentList: List<FilterTypeOfEmployment>) {
        selectedTypeOfEmploymentList.removeAll(selectedTypeOfEmploymentList.filter {
            !typeOfEmploymentList.contains(it)
        })
        selectedTypeOfEmploymentList.addAll(typeOfEmploymentList.filter {
            !selectedTypeOfEmploymentList.contains(it)
        })
    }
    //Filter - Selector Setter (end)

    //Filter - Slider Setter (start)
    fun setFilterGsmScoreSliderValues(gsmScoreSliderValue: ClosedFloatingPointRange<Float>) {
        filterGsmScoreSliderValues.value = gsmScoreSliderValue
    }

    fun setFilterDesiredAnnualSalarySliderValues(desiredAnnualSalarySliderValues: ClosedFloatingPointRange<Float>) {
        filterDesiredAnnualSalarySliderValues.value = desiredAnnualSalarySliderValues
    }

    fun setSelectedGsmScoreSliderValues(gsmScoreSliderValue: ClosedFloatingPointRange<Float>) {
        selectedGsmScoreSliderValues.value = gsmScoreSliderValue
    }

    fun setSelectedDesiredAnnualSalarySliderValues(desiredAnnualSalarySliderValues: ClosedFloatingPointRange<Float>) {
        selectedDesiredAnnualSalarySliderValues.value = desiredAnnualSalarySliderValues
    }
    //Filter - Slider Setter (end)

    //Filter - SelectionControl Setter (start)
    fun setFilterSchoolNumberAscendingValue(schoolNumberAscendingValue: Boolean) {
        filterSchoolNumberAscendingOrder.value = schoolNumberAscendingValue
    }

    fun setFilterGsmScoreAscendingValue(gsmScoreAscendingValue: Boolean) {
        filterGsmScoreAscendingOrder.value = gsmScoreAscendingValue
    }

    fun setFilterDesiredAnnualSalaryAscendingValue(desiredAnnualSalaryAscendingValue: Boolean) {
        filterDesiredAnnualSalaryAscendingOrder.value = desiredAnnualSalaryAscendingValue
    }

    fun setSelectedSchoolNumberAscendingValue(schoolNumberAscendingValue: Boolean) {
        selectedSchoolNumberAscendingOrder.value = schoolNumberAscendingValue
    }

    fun setSelectedGsmScoreAscendingValue(gsmScoreAscendingValue: Boolean) {
        selectedGsmScoreAscendingOrder.value = gsmScoreAscendingValue
    }

    fun setSelectedDesiredAnnualSalaryAscendingValue(desiredAnnualSalaryAscendingValue: Boolean) {
        selectedDesiredAnnualSalaryAscendingOrder.value = desiredAnnualSalaryAscendingValue
    }
    //Filter - SelectionControl Setter (end)

    //Filter - DetailStack Setter (start)
    fun setFilterDetailStackList(detailStacks: List<String>) {
        filterDetailStackList.removeAll(filterDetailStackList.filter {
            !detailStacks.contains(it)
        })
        filterDetailStackList.addAll(detailStacks.filter {
            !filterDetailStackList.contains(it)
        })
    }

    fun setSelectedDetailStackList(detailStacks: List<String>) {
        selectedDetailStack.removeAll(selectedDetailStack.filter {
            !detailStacks.contains(it)
        })
        selectedDetailStack.addAll(detailStacks.filter {
            !selectedDetailStack.contains(it)
        })
    }
    //Filter - DetailStack Setter (end)
}