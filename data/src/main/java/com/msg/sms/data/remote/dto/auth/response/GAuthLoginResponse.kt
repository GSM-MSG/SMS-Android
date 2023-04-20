package com.msg.sms.data.remote.dto.auth.response

data class GAuthLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)
