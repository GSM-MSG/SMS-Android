package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName

data class ProjectRelatedLinkData(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
