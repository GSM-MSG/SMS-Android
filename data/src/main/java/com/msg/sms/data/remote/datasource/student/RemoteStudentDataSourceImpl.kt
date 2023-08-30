package com.msg.sms.data.remote.datasource.student

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.request.PutChangedProfileRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentForAnonymousResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForStudentResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForTeacherResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import com.msg.sms.data.remote.network.api.StudentAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override suspend fun getUserDetailForStudent(uuid: UUID): Flow<GetStudentForStudentResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentForStudentResponse>().httpRequest {
                    service.getStudentForStudent(
                        uuid = uuid
                    )
                }.sendRequest()
            )
        }
    }

    override suspend fun getUserDetailForAnonymous(uuid: UUID): Flow<GetStudentForAnonymousResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentForAnonymousResponse>().httpRequest {
                    service.getStudentForAnonymous(
                        uuid = uuid
                    )
                }.sendRequest()
            )
        }
    }

    override suspend fun getUserDetailForTeacher(uuid: UUID): Flow<GetStudentForTeacherResponse> {
        return flow {
            emit(
                SMSApiHandler<GetStudentForTeacherResponse>().httpRequest {
                    service.getStudentForTeacher(
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
}