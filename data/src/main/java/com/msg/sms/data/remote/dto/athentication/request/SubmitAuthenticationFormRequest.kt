package com.msg.sms.data.remote.dto.athentication.request

data class SubmitAuthenticationFormRequest(
    val contents: List<AuthenticationSectionRequest>,
)