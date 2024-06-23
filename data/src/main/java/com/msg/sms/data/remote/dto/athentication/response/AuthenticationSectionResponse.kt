package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionModel

data class AuthenticationSectionResponse(
    val section: String,
    val sectionId: String,
    val maxCount: Int,
    val fields: List<AuthenticationSectionField>,
) {

    fun toAuthenticationSectionModel() = AuthenticationSectionModel(
        section = this.section,
        maxCount = this.maxCount,
        fields = this.fields.map { it.toAuthenticationSectionFieldModel() },
        sectionId = this.sectionId
    )

}