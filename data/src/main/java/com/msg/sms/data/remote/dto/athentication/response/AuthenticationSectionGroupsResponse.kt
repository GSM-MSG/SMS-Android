package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionGroupModel

data class AuthenticationSectionGroupsResponse(
    val groupId: String,
    val maxScore: Double,
    val fields: List<AuthenticationSectionFieldResponse>
)

fun AuthenticationSectionGroupsResponse.toAuthenticationSectionGroupModel() =
    AuthenticationSectionGroupModel(
        groupId = this.groupId,
        maxScore = this.maxScore,
        fields = this.fields.map { it.toAuthenticationSectionFieldModel() }
    )