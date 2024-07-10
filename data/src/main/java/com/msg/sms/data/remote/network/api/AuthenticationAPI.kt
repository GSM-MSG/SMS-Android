package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.athentication.request.SubmitAuthenticationFormRequest
import com.msg.sms.data.remote.dto.athentication.response.AuthenticationFormResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationAPI {

    @GET("authentication/form")
    suspend fun fetchAuthenticationForm(): AuthenticationFormResponse

    @POST("authentication/submit")
    suspend fun submitAuthenticationForm(
        @Body formData: SubmitAuthenticationFormRequest,
    )
}