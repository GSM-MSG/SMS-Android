package com.msg.sms.data.remote.network.api

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
}