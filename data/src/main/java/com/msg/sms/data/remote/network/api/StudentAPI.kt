package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
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
        @Query("size") size: Int,
        @Query("majors") majors: List<String>? = null,
        @Query("techStacks") techStacks: List<String>? = null,
        @Query("grade") grade: Int? = null,
        @Query("classNum") classNum: Int? = null,
        @Query("department") department: List<String>? = null,
        @Query("stuNumSort") stuNumSort: String? = null,
        @Query("formOfEmployment") formOfEmployment: String? = null,
        @Query("minGsmAuthenticationScore") minGsmAuthenticationScore: Int? = null,
        @Query("maxGsmAuthenticationScore") maxGsmAuthenticationScore: Int? = null,
        @Query("minSalary") minSalary: Int? = null,
        @Query("maxSalary") maxSalary: Int? = null,
        @Query("gsmAuthenticationScoreSort") gsmAuthenticationScoreSort: String? = null,
        @Query("salarySort") salarySort: String? = null
    ): GetStudentListResponse
}