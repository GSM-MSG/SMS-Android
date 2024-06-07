package com.msg.sms.domain.model.authentication

data class AuthenticationFormModel(
    val content: List<AuthenticationAreaModel>,
) {
    data class AuthenticationAreaModel(
        val title: String,
        val field: List<AuthenticationAreaFieldModel>,
        val items: List<AuthenticationSectionModel>,
    )

    data class AuthenticationAreaFieldModel(
        val name: String,
        val url: String,
    )

    data class AuthenticationSectionModel(
        val section: String,
        val scoreDescription: String?,
        val sectionScore: Int,
        val maxCount: Int,
        val fields: List<AuthenticationSectionFieldModel>,
    ) {
        data class AuthenticationSectionFieldModel(
            val key: String,
            val type: AuthenticationSelectionType,
            val values: List<AuthenticationSectionFieldValues>?,
            val example: String?,
        ) {
            data class AuthenticationSectionFieldValues(
                val selectId: String,
                val value: String,
            )
        }

        enum class AuthenticationSelectionType {
            TEXT, FILE, BOOLEAN, SELECT_VALUE
        }
    }
}

