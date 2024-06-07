package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.athentication.AuthenticationFormResponse
import retrofit2.http.GET

interface AuthenticationAPI {

    @GET("authentication/")
    suspend fun fetchAuthenticationForm(): AuthenticationFormResponse
}