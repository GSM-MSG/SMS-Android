package com.msg.sms.data.remote.dto.auth.response

import com.msg.sms.domain.model.auth.response.AccessValidationResponseModel

data class AccessValidationResponse(
    val isExist: Boolean
)

fun AccessValidationResponse.toModel() =
    AccessValidationResponseModel(
        isExist = this.isExist
    )