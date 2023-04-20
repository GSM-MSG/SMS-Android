package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel

interface RemoteAuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponse
}