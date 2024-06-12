package com.msg.sms.data.remote.dto.athentication

import com.msg.sms.domain.model.authentication.AuthenticationAreaFieldModel
import com.msg.sms.domain.model.authentication.AuthenticationAreaModel
import com.msg.sms.domain.model.authentication.AuthenticationFormModel
import com.msg.sms.domain.model.authentication.AuthenticationSectionFieldModel
import com.msg.sms.domain.model.authentication.AuthenticationSectionFieldValuesModel
import com.msg.sms.domain.model.authentication.AuthenticationSectionModel
import com.msg.sms.domain.model.authentication.AuthenticationSelectionType

data class AuthenticationFormResponse(
    val contents: List<AuthenticationArea>,
    val files: List<AuthenticationAreaField>,
) {
    fun toAuthenticationFormModel(): AuthenticationFormModel {
        return AuthenticationFormModel(
            contents = contents.map { it.toAuthenticationAreaModel() },
            files = files.map { it.toAuthenticationAreaFieldModel() }
        )
    }
}

data class AuthenticationArea(
    val title: String,
    val sections: List<AuthenticationSection>,
) {
    fun toAuthenticationAreaModel() = AuthenticationAreaModel(
        title = this.title,
        sections = this.sections.map { it.toAuthenticationSectionModel() }
    )
}

data class AuthenticationAreaField(
    val name: String,
    val url: String,
) {

    fun toAuthenticationAreaFieldModel() = AuthenticationAreaFieldModel(
        name = this.name,
        url = this.url
    )
}

data class AuthenticationSection(
    val section: String,
    val sectionId: String,
    val scoreDescription: String,
    val sectionScore: Int,
    val maxCount: Int,
    val fields: List<AuthenticationSectionField>,
) {

    fun toAuthenticationSectionModel() = AuthenticationSectionModel(
        section = this.section,
        scoreDescription = this.scoreDescription,
        sectionScore = this.sectionScore,
        maxCount = this.maxCount,
        fields = this.fields.map { it.toAuthenticationSectionFieldModel() },
        sectionId = this.sectionId
    )

}

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

data class AuthenticationSectionField(
    val key: String,
    val type: String,
    val values: List<AuthenticationSectionFieldValues>?,
    val example: String,
) {

    fun toAuthenticationSectionFieldModel() =
        AuthenticationSectionFieldModel(
            type = AuthenticationSelectionType.valueOf(
                this.type
            ),
            values = this.values?.map { it.toAuthenticationSectionFieldValuesModel() },
            example = this.example
        )
}