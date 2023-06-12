package com.msg.sms.domain.repository

import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.response.StudentListModel
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun enterStudentInformation(body: EnterStudentInformationModel): Flow<Unit>

    suspend fun getStudentList(
        page: Int,
        size: Int,
        majors: List<String>?,
        techStacks: List<String>?,
        grade: Int?,
        classNum: Int?,
        department: List<String>?,
        stuNumSort: String?,
        formOfEmployment: String?,
        minGsmAuthenticationScore: Int?,
        maxGsmAuthenticationScore: Int?,
        minSalary: Int?,
        maxSalary: Int?,
        gsmAuthenticationScoreSort: String?,
        salarySort: String?
    ): Flow<StudentListModel>
}