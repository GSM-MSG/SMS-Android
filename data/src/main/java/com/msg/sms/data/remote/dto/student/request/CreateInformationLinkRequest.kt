package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class CreateInformationLinkRequest(
    @SerializedName("studentId")
    val studentId: String,
    @SerializedName("periodDay")
    val periodDay: Long
)
