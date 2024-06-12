package com.msg.sms.domain.model.authentication

data class SendAuthenticationDataModel(
    val sectionId: String,
    val value: String? = null,
    val sectionType: AuthenticationSelectionType,
    val selectId: String? = null
)