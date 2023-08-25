package com.msg.sms.data.remote.dto.user.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.user.response.LinkModel
import com.msg.sms.domain.model.user.response.ProjectModel

data class ProjectResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("previewImages")
    val previewImages: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("links")
    val links: LinkModel,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("myActivity")
    val myActivity: String,
    @SerializedName("inProgress")
    val inProgress: ProgressResponse
)

fun ProjectResponse.toProjectModel(): ProjectModel {
    return ProjectModel(
        name = this.name,
        icon = this.icon,
        previewImages = previewImages,
        description = description,
        links = links,
        techStacks = techStacks,
        myActivity = myActivity,
        inProgress = this.inProgress.toProgressModel()
    )
}