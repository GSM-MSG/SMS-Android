package com.msg.sms.data.remote.dto.auth.response

import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel

data class GAuthLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)

fun GAuthLoginResponse.toLoginModel() =
    GAuthLoginResponseModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        accessTokenExp = this.accessTokenExp,
        refreshTokenExp = this.refreshTokenExp
    )
