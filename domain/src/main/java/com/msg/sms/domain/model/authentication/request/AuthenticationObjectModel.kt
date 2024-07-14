package com.msg.sms.domain.model.authentication.request

data class AuthenticationObjectModel(
    val groupId: String,
    val fields: List<AuthenticationFieldModel>
)