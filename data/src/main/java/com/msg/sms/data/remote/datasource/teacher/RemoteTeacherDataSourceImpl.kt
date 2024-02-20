package com.msg.sms.data.remote.datasource.teacher

import com.msg.sms.data.remote.network.api.TeacherAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteTeacherDataSourceImpl @Inject constructor(
    private val service: TeacherAPI
) : RemoteTeacherDataSource {
    override suspend fun common(): Flow<Unit> = flow {
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.common() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun principal(): Flow<Unit> = flow{
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.principal() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun vicePrincipal(): Flow<Unit> = flow {
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.vicePrincipal() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun headOfDepartment(): Flow<Unit> = flow {
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.headOfDepartment() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}