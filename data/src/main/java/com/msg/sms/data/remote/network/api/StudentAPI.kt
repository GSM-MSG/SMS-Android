package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.request.PutChangedProfileRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentListResponse
import retrofit2.http.*
import java.util.*

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
        @Query("grade") grade: List<Int>?,
        @Query("classNum") classNum: List<Int>?,
        @Query("department") department: List<String>?,
        @Query("stuNumSort") stuNumSort: String?,
        @Query("formOfEmployment") formOfEmployment: List<String>?,
        @Query("minGsmAuthenticationScore") minGsmAuthenticationScore: Int?,
        @Query("maxGsmAuthenticationScore") maxGsmAuthenticationScore: Int?,
        @Query("minSalary") minSalary: Int?,
        @Query("maxSalary") maxSalary: Int?,
        @Query("gsmAuthenticationScoreSort") gsmAuthenticationScoreSort: String?,
        @Query("salarySort") salarySort: String?,
    ): GetStudentListResponse

    @GET("student/{role}{uuid}")
    suspend fun getUserDetail(
        @Path("role") role: String,
        @Path("uuid") uuid: UUID
    ): GetStudentResponse

    @PUT("student")
    suspend fun putChangedProfile(
        @Body body: PutChangedProfileRequest
    )
}