package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentAPI {
    @POST("student")
    suspend fun enterStudentInformation(
        @Body body: EnterStudentInformationRequest
    )

    @GET("student")
    suspend fun getStudentList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("majors") majors: List<String>?,
        @Query("techStacks") techStacks: List<String>?,
        @Query("grade") grade: Int?,
        @Query("classNum") classNum: Int?,
        @Query("department") department: List<String>?,
        @Query("stuNumSort") stuNumSort: String?,
        @Query("formOfEmployment") formOfEmployment: String?,
        @Query("minGsmAuthenticationScore") minGsmAuthenticationScore: Int?,
        @Query("maxGsmAuthenticationScore") maxGsmAuthenticationScore: Int?,
        @Query("minSalary") minSalary: Int?,
        @Query("maxSalary") maxSalary: Int?,
        @Query("gsmAuthenticationScoreSort") gsmAuthenticationScoreSort: String?,
        @Query("salarySort") salarySort: String?
    ): GetStudentListResponse
}