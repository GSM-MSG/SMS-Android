package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.mapper.LoginMapper
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.data.remote.network.api.AuthAPI
import com.msg.sms.data.util.SMSApiHandler
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val service: AuthAPI
) : RemoteAuthDataSource {
    override suspend fun gAuthLogin(body: GAuthLoginRequestModel): Flow<GAuthLoginResponse> =
        SMSApiHandler<Flow<GAuthLoginResponse>>()
            .httpRequest {
                flow {
                    emit(service.gAuthLogin(body = LoginMapper.toLoginDto(body)))
                }.flowOn(Dispatchers.IO)
            }
            .sendRequest()
}