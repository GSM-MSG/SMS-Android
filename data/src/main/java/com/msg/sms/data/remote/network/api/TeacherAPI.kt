package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.teacher.request.HomeroomTeacherRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface TeacherAPI {
    @POST("/teacher/common")
    suspend fun common()

    @POST("/teacher/principal")
    suspend fun principal()

    @POST("/teacher/deputy-principal")
    suspend fun vicePrincipal()

    @POST("/teacher/director")
    suspend fun headOfDepartment()

    @POST("/teacher/homeroom")
    suspend fun homeroom(
        @Body body: HomeroomTeacherRequest,
    )
}