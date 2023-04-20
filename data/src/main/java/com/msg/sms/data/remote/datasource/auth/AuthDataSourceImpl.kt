package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.data.util.SMSApiHandler
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthDataSource {
    override suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse =
        SMSApiHandler<GAuthLoginResponse>()
            .httpRequest { dataSource.gAuthLogin(body = body) }
            .sendRequest()
}