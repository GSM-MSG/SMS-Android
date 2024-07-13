package com.msg.sms.domain.model.authentication.request

import android.net.Uri
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType

data class AtomicAuthenticationFieldModel(
    val fieldId: String,
    val fieldType: AuthenticationFieldType,
    val value: String?,
    val selectId: String?,
    val groupId: String = "",
    val sectionId: String = "",
    val groupIndex: Int = 0,
    val file: Uri? = null,
)