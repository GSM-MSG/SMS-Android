package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StudentAPI {
    @POST("student")
    suspend fun enterStudentInformation(
        @Body body: EnterStudentInformationRequest
    ): Response<Unit>
}