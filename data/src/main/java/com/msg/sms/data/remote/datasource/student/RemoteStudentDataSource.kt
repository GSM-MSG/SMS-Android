package com.msg.sms.data.remote.datasource.student

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteStudentDataSource {
    suspend fun enterStudentInformation(body: EnterStudentInformationRequest): Flow<Unit>

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
    ): Flow<GetStudentListResponse>
}