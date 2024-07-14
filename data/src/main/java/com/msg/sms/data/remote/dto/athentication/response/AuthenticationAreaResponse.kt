package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationAreaModel

data class AuthenticationAreaResponse(
    val title: String,
    val sections: List<AuthenticationSectionResponse>,
) {
    fun toAuthenticationAreaModel() = AuthenticationAreaModel(
        title = this.title,
        sections = this.sections.map { it.toAuthenticationSectionModel() }
    )
}