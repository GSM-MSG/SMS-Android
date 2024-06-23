package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType

data class AuthenticationSectionField(
    val fieldId: String,
    val fieldType: String,
    val scoreDescription : String,
    val values: List<AuthenticationSectionFieldValues>?,
    val example: String,
) {

    fun toAuthenticationSectionFieldModel() =
        AuthenticationSectionFieldModel(
            fieldId = this.fieldId,
            fieldType = AuthenticationFieldType.valueOf(
                this.fieldType
            ),
            scoreDescription = this.scoreDescription,
            values = this.values?.map { it.toAuthenticationSectionFieldValuesModel() },
            example = this.example
        )
}