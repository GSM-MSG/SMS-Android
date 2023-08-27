package com.msg.sms.domain.model.user.response

data class ProjectModel(
    val name: String,
    val icon: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<LinkModel>,
    val techStacks: List<String>,
    val myActivity: String,
    val inProgress: ProgressModel
)
