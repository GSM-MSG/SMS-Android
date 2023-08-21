package com.msg.sms.domain.model.student.request

data class ProjectModel(
    val name: String,
    val icon: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<ProjectRelatedLinkModel>,
    val techStacks: List<String>,
    val myActivity: String,
    val inProgress: ProjectDateModel
)
