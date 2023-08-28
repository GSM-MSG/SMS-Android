package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class CertificateData(
    @SerializedName("languageCertificateName")
    val languageCertificateName: String,
    @SerializedName("score")
    val score: String
)
