package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.common.CertificateData
import com.msg.sms.data.remote.dto.common.PrizeData
import com.msg.sms.data.remote.dto.common.ProjectData

data class EnterStudentInformationRequest(
    @SerializedName("major")
    val major: String,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("profileImgUrl")
    val profileImgUrl: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("contactEmail")
    val contactEmail: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("regions")
    val regions: List<String>,
    @SerializedName("languageCertificates")
    val languageCertificates: List<CertificateData>,
    @SerializedName("militaryService")
    val militaryService: String,
    @SerializedName("certificates")
    val certificates: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectData>,
    @SerializedName("prizes")
    val prizes: List<PrizeData>
)
