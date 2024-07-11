package com.msg.sms.domain.repository

import com.msg.sms.domain.model.student.request.CreateInformationLinkRequestModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.request.PutChangeProfileRequestModel
import com.msg.sms.domain.model.student.response.CreateInformationLinkResponseModel
import com.msg.sms.domain.model.student.response.GetStudentModel
import com.msg.sms.domain.model.student.response.StudentListModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
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

    suspend fun getUserDetail(role: String, uuid: UUID): Flow<GetStudentModel>

    suspend fun putChangedProfile(profile: PutChangeProfileRequestModel): Flow<Unit>

    suspend fun createInformationLink(body: CreateInformationLinkRequestModel): Flow<CreateInformationLinkResponseModel>

    suspend fun putChangedPortfolioPdf(file: MultipartBody.Part): Flow<Unit>
}