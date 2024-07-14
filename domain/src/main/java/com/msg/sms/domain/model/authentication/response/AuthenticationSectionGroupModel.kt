package com.msg.sms.domain.model.authentication.response

data class AuthenticationSectionGroupModel(
    val groupId: String,
    val maxScore: Double,
    val fields: List<AuthenticationSectionFieldModel>
)