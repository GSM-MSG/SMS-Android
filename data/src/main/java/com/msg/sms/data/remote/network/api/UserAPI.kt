package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.user.response.GetMyProfileResponse
import com.msg.sms.data.remote.dto.user.response.GetProfileImageResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("user/profile/img")
    suspend fun getProfileImage(): GetProfileImageResponse

    @GET("user/profile")
    suspend fun getMyProfile(): GetMyProfileResponse
}