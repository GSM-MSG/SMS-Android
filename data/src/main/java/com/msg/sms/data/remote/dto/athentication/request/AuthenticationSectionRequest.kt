package com.msg.sms.data.remote.dto.athentication.request

data class AuthenticationSectionRequest(
    val sectionId: String,
    val objects: List<AuthenticationFieldRequest>
)