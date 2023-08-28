package com.msg.sms.data.remote.datasource.student

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.request.PutChangedProfileRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentForAnonymousResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForStudentResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForTeacherResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import kotlinx.coroutines.flow.Flow
import java.util.*

interface RemoteStudentDataSource {
    suspend fun enterStudentInformation(body: EnterStudentInformationRequest): Flow<Unit>

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
        salarySort: String?,
    ): Flow<GetStudentListResponse>

    suspend fun getUserDetailForStudent(uuid: UUID): Flow<GetStudentForStudentResponse>

    suspend fun getUserDetailForAnonymous(uuid: UUID): Flow<GetStudentForAnonymousResponse>

    suspend fun getUserDetailForTeacher(uuid: UUID): Flow<GetStudentForTeacherResponse>

    suspend fun putChangedProfile(body: PutChangedProfileRequest): Flow<Unit>
}