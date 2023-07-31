package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.major.MajorListResponse
import retrofit2.http.GET

interface MajorAPI {

    @GET("major/list")
    suspend fun getMajorList(): MajorListResponse
}