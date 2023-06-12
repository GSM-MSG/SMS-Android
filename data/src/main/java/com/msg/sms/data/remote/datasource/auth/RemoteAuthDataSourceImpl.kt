package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.data.remote.network.api.AuthAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val service: AuthAPI
) : RemoteAuthDataSource {
    override suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse =
        SMSApiHandler<GAuthLoginResponse>()
            .httpRequest { service.gAuthLogin(body = body) }
            .sendRequest()

    override suspend fun accessValidation(): Flow<Unit> {
        return flow {
            emit(
                SMSApiHandler<Unit>()
                    .httpRequest { service.accessValidation() }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }
}