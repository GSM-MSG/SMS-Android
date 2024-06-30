package com.msg.sms.data.remote.datasource.student

import android.util.Log
import com.msg.sms.data.remote.dto.student.request.CreateInformationLinkRequest
import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.request.PutChangedProfileRequest
import com.msg.sms.data.remote.dto.student.response.CreateInformationLinkResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentResponse
import com.msg.sms.data.remote.network.api.StudentAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import java.util.UUID
import javax.inject.Inject

class RemoteStudentDataSourceImpl @Inject constructor(
    private val service: StudentAPI,
) : RemoteStudentDataSource {
    override suspend fun enterStudentInformation(body: EnterStudentInformationRequest): Flow<Unit> {
        return flow {
            emit(
                SMSApiHandler<Unit>()
                    .httpRequest { service.enterStudentInformation(body = body) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getStudentList(
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
    ): Flow<GetStudentListResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentListResponse>()
                    .httpRequest {
                        service.getStudentList(
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
                        )
                    }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserDetail(
        role: String,
        uuid: UUID,
    ): Flow<GetStudentResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentResponse>().httpRequest {
                    service.getUserDetail(
                        uuid = uuid
                    )
                }.sendRequest()
            )
        }
    }

    override suspend fun getUserDetailRole(
        role: String,
        uuid: UUID
    ): Flow<GetStudentResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentResponse>().httpRequest {
                    service.getUserDetailRole(
                        role = role,
                        uuid = uuid
                    )
                }.sendRequest()
            )
        }
    }

    override suspend fun putChangedProfile(body: PutChangedProfileRequest): Flow<Unit> {
        return flow {
            emit(SMSApiHandler<Unit>().httpRequest {
                service.putChangedProfile(body = body)
            }.sendRequest())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun createInformationLink(body: CreateInformationLinkRequest): Flow<CreateInformationLinkResponse> {
        return flow {
            emit(SMSApiHandler<CreateInformationLinkResponse>().httpRequest {
                service.createInformationLink(body = body)
            }.sendRequest())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun putChangedPortfolioPdf(file: MultipartBody.Part): Flow<Unit> {
        Log.d("testt-start", file.toString())
        SMSApiHandler<Unit>().httpRequest {
            service.putChangedPortfolioPdf(file = file)
            Log.d("testt-end", file.toString())
        }.sendRequest()
        return flow {
            emit(Unit)
        }.flowOn(Dispatchers.IO)
    }
}