package com.msg.sms.data.remote.network.api

import retrofit2.http.POST

interface TeacherAPI {
    @POST("/teacher/common")
    suspend fun common()
}