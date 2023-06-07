package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentAPI {
    @POST("student")
    suspend fun enterStudentInformation(
        @Body body: EnterStudentInformationRequest
    ): Response<Unit>

    @GET("student")
    suspend fun getStudentList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<Unit>
}