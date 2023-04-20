package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth")
    suspend fun gAuthLogin(
        @Body body: GAuthLoginRequest
    ): GAuthLoginResponse
}