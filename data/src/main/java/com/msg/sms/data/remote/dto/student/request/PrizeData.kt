package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class PrizeData(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)
