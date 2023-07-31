package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.AccessValidationResponse
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.data.remote.network.api.AuthAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val service: AuthAPI,
) : RemoteAuthDataSource {
    override suspend fun gAuthLogin(body: GAuthLoginRequest): Flow<GAuthLoginResponse> = flow {
        emit(
            SMSApiHandler<GAuthLoginResponse>()
            .httpRequest { service.gAuthLogin(body = body) }
            .sendRequest()
        )
    }.flowOn(Dispatchers.IO)


    override suspend fun accessValidation(): Flow<AccessValidationResponse> {
        return flow {
            emit(
                SMSApiHandler<AccessValidationResponse>()
                    .httpRequest { service.accessValidation() }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun logout(): Flow<Unit> = flow {
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.logout() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun withdrawal(): Flow<Unit> = flow {
        emit(
            SMSApiHandler<Unit>()
                .httpRequest { service.withdrawal() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}