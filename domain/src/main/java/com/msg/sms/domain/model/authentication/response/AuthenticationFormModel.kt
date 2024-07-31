package com.msg.sms.domain.model.authentication.response

data class AuthenticationFormModel(
    val contents: List<AuthenticationAreaModel>,
    val files: List<FileModel>,
)