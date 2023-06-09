package com.sms.presentation.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.model.student.response.StudentModel
import com.msg.sms.domain.usecase.student.GetStudentListUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase
) : ViewModel() {
    private var studentList = mutableStateListOf<StudentModel>()

    private val _getStudentListResponse = MutableStateFlow<Event<StudentListModel>>(Event.Loading)
    val getStudentListResponse = _getStudentListResponse.asStateFlow()

    fun getStudentListData(): List<StudentModel> {
        return studentList
    }

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
        salarySort: String? = null
    ) = viewModelScope.launch {
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
                studentList = response.content.toMutableStateList()
            }
        }.onFailure { error ->
            _getStudentListResponse.value = error.errorHandling()
        }
    }
}