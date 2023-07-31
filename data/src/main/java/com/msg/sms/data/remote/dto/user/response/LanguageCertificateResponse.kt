package com.msg.sms.data.remote.dto.user.response

import com.google.gson.annotations.SerializedName

data class LanguageCertificateResponse(
    @SerializedName("languageCertificateName")
    val languageCertificateName: String,
    @SerializedName("score")
    val score: String,
)
