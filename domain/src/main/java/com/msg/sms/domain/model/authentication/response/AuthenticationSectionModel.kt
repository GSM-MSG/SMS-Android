package com.msg.sms.domain.model.authentication.response

data class AuthenticationSectionModel(
    val sectionId: String,
    val sectionName: String,
    val maxCount: Int,
    val groups: List<AuthenticationSectionGroupModel>,
)