package com.msg.sms.domain.repository

import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.response.GetStudentForAnonymousModel
import com.msg.sms.domain.model.student.response.GetStudentForStudentModel
import com.msg.sms.domain.model.student.response.GetStudentForTeacherModel
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.model.user.response.MyProfileModel
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

    suspend fun getUserDetailForStudent(uuid: UUID): Flow<GetStudentForStudentModel>

    suspend fun getUserDetailForAnonymous(uuid: UUID): Flow<GetStudentForAnonymousModel>

    suspend fun getUserDetailForTeacher(uuid: UUID): Flow<GetStudentForTeacherModel>

    suspend fun putChangedProfile(profile: MyProfileModel): Flow<Unit>
}