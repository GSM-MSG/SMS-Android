package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType

data class AuthenticationSectionFieldResponse(
    val fieldId: String,
    val fieldType: String,
    val scoreDescription : String,
    val values: List<AuthenticationSectionFieldValuesResponse>?,
    val placeholder: String,
) {

    fun toAuthenticationSectionFieldModel() =
        AuthenticationSectionFieldModel(
            fieldId = this.fieldId,
            fieldType = AuthenticationFieldType.valueOf(
                this.fieldType
            ),
            scoreDescription = this.scoreDescription,
            values = this.values?.map { it.toAuthenticationSectionFieldValuesModel() },
            placeholder = this.placeholder
        )
}