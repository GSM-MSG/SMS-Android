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
    @SerializedName("prizes")
    val prizes: List<PrizeData>
)
