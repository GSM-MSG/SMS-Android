package com.msg.sms.domain.model.auth.response

data class GAuthLoginResponseData(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)
