package com.msg.sms.domain.model.authentication.response

data class AuthenticationSectionFieldModel(
    // 클라에서 데이터를 저장할 때 사용할 uuid
    val uuid: String = "",
    val fieldId: String,
    val fieldType: AuthenticationFieldType,
    val scoreDescription : String?,
    val values: List<AuthenticationSectionFieldValuesModel>?,
    val placeholder: String,
)