package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.athentication.AuthenticationFormResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthenticationAPI {

    @GET("authentication/form/{uuid}")
    suspend fun fetchAuthenticationForm(@Path("uuid") uuid: String): AuthenticationFormResponse
}