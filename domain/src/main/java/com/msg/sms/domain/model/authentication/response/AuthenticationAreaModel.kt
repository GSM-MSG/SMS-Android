package com.msg.sms.domain.model.authentication.response

data class AuthenticationAreaModel(
    val title: String,
    val sections: List<AuthenticationSectionModel>,
)