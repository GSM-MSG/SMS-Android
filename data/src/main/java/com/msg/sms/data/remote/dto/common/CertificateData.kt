package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.request.CertificateInformationModel

data class CertificateData(
    @SerializedName("languageCertificateName")
    val languageCertificateName: String,
    @SerializedName("score")
    val score: String
)

fun CertificateData.toCertificateModel(): CertificateInformationModel {
    return CertificateInformationModel(
        languageCertificateName = this.languageCertificateName,
        score = this.score
    )
}
