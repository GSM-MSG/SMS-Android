package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.common.ProjectModel

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

fun ProjectData.toProjectModel(): ProjectModel {
    return ProjectModel(
        name = this.name,
        icon = this.icon,
        previewImages = this.previewImages,
        description = this.description,
        links = this.links.map { it.toProjectRelatedLinkModel() },
        techStacks = this.techStacks,
        task = this.myActivity,
        activityDuration = this.inProgress.toProjectDateModel(),
    )
}