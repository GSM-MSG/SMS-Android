package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldValuesModel

data class AuthenticationSectionFieldValues(
    val selectId: String,
    val value: String,
) {
    fun toAuthenticationSectionFieldValuesModel() =
        AuthenticationSectionFieldValuesModel(
            selectId = this.selectId,
            value = this.value
        )
}