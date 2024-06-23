package com.msg.sms.domain.model.authentication.response

data class AuthenticationSectionModel(
    val sectionId: String,
    val section: String,
    val maxCount: Int,
    val fields: List<AuthenticationSectionFieldModel>,
)