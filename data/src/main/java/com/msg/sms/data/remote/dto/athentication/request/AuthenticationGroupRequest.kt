package com.msg.sms.data.remote.dto.athentication.request

data class AuthenticationGroupRequest(
    val groupId: String,
    val fields: List<AuthenticationFieldRequest>,
)