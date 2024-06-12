package com.msg.sms.domain.model.authentication

data class AuthenticationFormModel(
    val contents: List<AuthenticationAreaModel>,
    val files: List<AuthenticationAreaFieldModel>,
)

data class AuthenticationAreaModel(
    val title: String,
    val sections: List<AuthenticationSectionModel>,
)

data class AuthenticationAreaFieldModel(
    val name: String,
    val url: String,
)

data class AuthenticationSectionModel(
    val sectionId: String,
    val section: String,
    val scoreDescription: String?,
    val sectionScore: Int,
    val maxCount: Int,
    val fields: List<AuthenticationSectionFieldModel>,
)

data class AuthenticationSectionFieldModel(
    val type: AuthenticationSelectionType,
    val values: List<AuthenticationSectionFieldValuesModel>?,
    val example: String?,
)

data class AuthenticationSectionFieldValuesModel(
    val selectId: String,
    val value: String,
)