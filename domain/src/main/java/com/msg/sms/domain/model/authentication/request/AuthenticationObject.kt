package com.msg.sms.domain.model.authentication.request

import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType

data class AuthenticationObject(
    val fieldId: String,
    val value: String,
    val selectId: String,
    val fieldType: AuthenticationFieldType,
)