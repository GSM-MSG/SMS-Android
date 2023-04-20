package com.msg.sms.data.remote.dto.auth.response

import com.msg.sms.domain.model.auth.response.GAuthLoginResponseData

data class GAuthLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)

fun GAuthLoginResponse.toLoginData() =
    GAuthLoginResponseData(
        accessToken = accessToken,
        refreshToken = refreshToken,
        accessTokenExp = accessTokenExp,
        refreshTokenExp = refreshTokenExp
    )
