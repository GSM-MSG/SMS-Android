package com.msg.sms.domain.model.authentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionModel

data class AuthenticationAreaModel(
    val title: String,
    val sections: List<AuthenticationSectionModel>,
)