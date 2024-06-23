package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.athentication.request.SubmitAuthenticationFormRequest
import com.msg.sms.data.remote.dto.athentication.response.AuthenticationFormResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationAPI {

    @GET("authentication/form/{uuid}")
    suspend fun fetchAuthenticationForm(@Path("uuid") uuid: String): AuthenticationFormResponse

    @POST("authentication/submit/{uuid}")
    suspend fun submitAuthenticationForm(
        @Path("uuid") uuid: String,
        @Body formData: SubmitAuthenticationFormRequest,
    )
}