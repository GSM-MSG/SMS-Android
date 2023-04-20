package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse

interface AuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse
}