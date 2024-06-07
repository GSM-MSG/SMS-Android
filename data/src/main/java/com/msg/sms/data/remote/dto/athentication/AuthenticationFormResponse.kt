package com.msg.sms.data.remote.dto.athentication

import com.msg.sms.domain.model.authentication.AuthenticationFormModel

data class AuthenticationFormResponse(
    val content: List<AuthenticationArea>,
) {
    data class AuthenticationArea(
        val title: String,
        val field: List<AuthenticationAreaField>,
        val items: List<AuthenticationSection>,
    ) {

        fun toAuthenticationAreaModel() = AuthenticationFormModel.AuthenticationAreaModel(
            title = this.title,
            field = this.field.map { it.toAuthenticationAreaFieldModel() },
            items = this.items.map { it.toAuthenticationSectionModel() }
        )
    }

    data class AuthenticationAreaField(
        val name: String,
        val url: String,
    ) {

        fun toAuthenticationAreaFieldModel() = AuthenticationFormModel.AuthenticationAreaFieldModel(
            name = this.name,
            url = this.url
        )
    }

    data class AuthenticationSection(
        val section: String,
        val scoreDescription: String,
        val sectionScore: Int,
        val maxCount: Int,
        val fields: List<AuthenticationSectionField>,
    ) {
        data class AuthenticationSectionField(
            val key: String,
            val type: String,
            val values: List<AuthenticationSectionFieldValues>?,
            val example: String,
        ) {
            data class AuthenticationSectionFieldValues(
                val selectId: String,
                val value: String,
            ) {

                fun toAuthenticationSectionFieldValuesModel() =
                    AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSectionFieldModel.AuthenticationSectionFieldValues(
                        selectId = this.selectId,
                        value = this.value
                    )
            }

            fun toAuthenticationSectionFieldModel() =
                AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSectionFieldModel(
                    key = this.key,
                    type = AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.valueOf(
                        this.type
                    ),
                    values = this.values?.map { it.toAuthenticationSectionFieldValuesModel() },
                    example = this.example
                )
        }

        fun toAuthenticationSectionModel() = AuthenticationFormModel.AuthenticationSectionModel(
            section = this.section,
            scoreDescription = this.scoreDescription,
            sectionScore = this.sectionScore,
            maxCount = this.maxCount,
            fields = this.fields.map { it.toAuthenticationSectionFieldModel() },
        )
    }

    fun toAuthenticationFormModel(): AuthenticationFormModel {
        return AuthenticationFormModel(
            content = content.map { it.toAuthenticationAreaModel() }
        )
    }
}



