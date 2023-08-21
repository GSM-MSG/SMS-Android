package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class EnterStudentInformationRequest(
    @SerializedName("major")
    val major: String,
    @SerializedName("techStack")
    val techStacks: List<String>,
    @SerializedName("profileImgUrl")
    val profileImgUrl: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("portfolioUrl")
    val portfolioUrl: String,
    @SerializedName("contactEmail")
    val contactEmail: String,
    @SerializedName("formOfEmployment")
    val formOfEmployment: String,
    @SerializedName("gsmAuthenticationScore")
    val gsmAuthenticationScore: Int,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("region")
    val regions: List<String>,
    @SerializedName("languageCertificate")
    val languageCertificates: List<CertificateData>,
    @SerializedName("militaryService")
    val militaryService: String,
    @SerializedName("certificate")
    val certificates: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectData>,
    @SerializedName("prize")
    val prizes: List<PrizeData>
)
