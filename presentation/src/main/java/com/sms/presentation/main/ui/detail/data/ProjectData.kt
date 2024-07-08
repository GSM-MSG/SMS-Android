package com.sms.presentation.main.ui.detail.data

import com.msg.sms.domain.model.user.response.ActivityDuration

data class ProjectData(
    val name: String,
    val activityDuration: ActivityDuration,
    val projectImage: List<String>,
    val icon: String,
    val description: String,
    val techStacks: List<String>,
    val keyTask: String,
    val relatedLinks: List<RelatedLinksData>
)
