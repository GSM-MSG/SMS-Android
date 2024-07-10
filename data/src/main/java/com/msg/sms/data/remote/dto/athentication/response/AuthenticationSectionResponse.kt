package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionModel

data class AuthenticationSectionResponse(
    val sectionName: String,
    val sectionId: String,
    val maxCount: Int,
    val groups: List<AuthenticationSectionGroupsResponse>,
) {
    fun toAuthenticationSectionModel() = AuthenticationSectionModel(
        sectionName = this.sectionName,
        maxCount = this.maxCount,
        groups = this.groups.map { it.toAuthenticationSectionGroupModel() },
        sectionId = this.sectionId
    )
}