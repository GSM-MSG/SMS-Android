package com.msg.sms.domain.model.authentication.request

data class SubmitAuthenticationFormModel(
    val sectionId: String,
    val objects: List<AuthenticationObject>,
)