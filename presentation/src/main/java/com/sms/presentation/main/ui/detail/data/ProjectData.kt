package com.sms.presentation.main.ui.detail.data

data class ProjectData(
    val name: String,
    val activityDuration: String,
    val projectImage: List<String>,
    val icon: String,
    val techStack: List<String>,
    val keyTask: String,
    val relatedLinks: List<RelatedLinksData>
)
