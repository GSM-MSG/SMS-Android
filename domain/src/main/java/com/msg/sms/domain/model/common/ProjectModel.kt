package com.msg.sms.domain.model.common

import com.msg.sms.domain.model.user.response.ActivityDuration

data class ProjectModel(
    val name: String,
    val icon: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<LinkModel>,
    val techStacks: List<String>,
    val task: String,
    val activityDuration: ActivityDuration
)
