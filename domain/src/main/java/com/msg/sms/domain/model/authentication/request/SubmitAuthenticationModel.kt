package com.msg.sms.domain.model.authentication.request

data class SubmitAuthenticationModel(
    val contents: List<SubmitAuthenticationFormModel>
)