package com.msg.sms.data.remote.dto.student.request

import com.google.gson.annotations.SerializedName

data class ProjectData(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("previewImages")
    val previewImages: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("links")
    val links: List<ProjectRelatedLinkData>,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("myActivity")
    val myActivity: String,
    @SerializedName("inProgress")
    val inProgress: ProjectDateData
)
