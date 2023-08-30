package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName

data class PrizeData(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)
