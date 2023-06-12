package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.AccessValidationResponse
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import kotlinx.coroutines.flow.Flow

interface RemoteAuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse

    suspend fun accessValidation(): Flow<AccessValidationResponse>
}