package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.user.response.GetProfileImageResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("profile")
    suspend fun getProfileImage(): GetProfileImageResponse
}