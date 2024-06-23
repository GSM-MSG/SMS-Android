package com.msg.sms.domain.model.authentication.response

data class AuthenticationSectionFieldModel(
    val fieldId: String,
    val fieldType: AuthenticationFieldType,
    val scoreDescription : String?,
    val values: List<AuthenticationSectionFieldValuesModel>?,
    val example: String,
)