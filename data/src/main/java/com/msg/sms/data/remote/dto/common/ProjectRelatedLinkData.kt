package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.common.ProjectRelatedLinkModel

data class ProjectRelatedLinkData(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun ProjectRelatedLinkData.toProjectRelatedLinkModel(): ProjectRelatedLinkModel {
    return ProjectRelatedLinkModel(
        name = this.name,
        url = this.url
    )
}
