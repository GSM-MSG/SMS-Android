package com.msg.sms.domain.repository

import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.response.GetStudentForAnonymous
import com.msg.sms.domain.model.student.response.GetStudentForStudent
import com.msg.sms.domain.model.student.response.GetStudentForTeacher
import com.msg.sms.domain.model.student.response.StudentListModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface StudentRepository {
    suspend fun enterStudentInformation(body: EnterStudentInformationModel): Flow<Unit>

    suspend fun getStudentList(
        page: Int,
        size: Int,
        majors: List<String>?,
        techStacks: List<String>?,
        grade: List<Int>?,
        classNum: List<Int>?,
        department: List<String>?,
        stuNumSort: String?,
        formOfEmployment: List<String>?,
        minGsmAuthenticationScore: Int?,
        maxGsmAuthenticationScore: Int?,
        minSalary: Int?,
        maxSalary: Int?,
        gsmAuthenticationScoreSort: String?,
        salarySort: String?
    ): Flow<StudentListModel>

    suspend fun getUserDetailForStudent(uuid: UUID): Flow<GetStudentForStudent>

    suspend fun getUserDetailForAnonymous(uuid: UUID): Flow<GetStudentForAnonymous>

    suspend fun getUserDetailForTeacher(uuid: UUID): Flow<GetStudentForTeacher>
}