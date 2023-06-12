package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteAuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse

    suspend fun accessValidation(): Flow<Unit>
}