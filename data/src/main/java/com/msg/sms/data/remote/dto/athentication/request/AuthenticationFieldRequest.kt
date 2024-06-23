package com.msg.sms.data.remote.dto.athentication.request

data class AuthenticationFieldRequest(
    val fieldId: String,
    val fieldType: String,
    val value: String,
    val selectId: String
)