package com.msg.sms.data.remote.dto.user.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.user.response.LinkModel

data class LinkResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)

fun LinkResponse.toLinkModel(): LinkModel {
    return LinkModel(
        name = this.name,
        url = this.url
    )
}