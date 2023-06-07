package com.msg.sms.data.remote.datasource.student

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.network.api.StudentAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteStudentDataSourceImpl @Inject constructor(
    private val service: StudentAPI
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

    override suspend fun getStudentList(page: Int, size: Int): Flow<Unit> {
        return flow {
            emit(
                SMSApiHandler<Unit>()
                    .httpRequest { service.getStudentList(page = page, size = size) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }
}