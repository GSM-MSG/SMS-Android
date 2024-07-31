package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel

data class AuthenticationFormResponse(
    val contents: List<AuthenticationAreaResponse>,
    val files: List<FileResponse>,
) {
    fun toAuthenticationFormModel(): AuthenticationFormModel {
        return AuthenticationFormModel(
            contents = contents.map { it.toAuthenticationAreaModel() },
            files = files.map { it.toFileModel() }
        )
    }
}