package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.student.response.GetStudentForAnonymousResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForStudentResponse
import com.msg.sms.data.remote.dto.student.response.GetStudentForTeacherResponse
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
        @Query("salarySort") salarySort: String?,
    ): GetStudentListResponse

    @GET("student/anonymous/{uuid}")
    suspend fun getStudentForAnonymous(
        @Path("uuid") uuid: UUID,
    ): GetStudentForAnonymousResponse

    @GET("student/{uuid}")
    suspend fun getStudentForStudent(
        @Path("uuid") uuid: UUID,
    ): GetStudentForStudentResponse

    @GET("student/teacher/{uuid}")
    suspend fun getStudentForTeacher(
        @Path("uuid") uuid: UUID,
    ): GetStudentForTeacherResponse
}