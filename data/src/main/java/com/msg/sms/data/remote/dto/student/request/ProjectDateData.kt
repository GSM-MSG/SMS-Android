package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class ProjectDateData(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)