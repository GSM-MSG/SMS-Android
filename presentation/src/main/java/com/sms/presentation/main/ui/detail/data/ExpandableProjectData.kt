package com.sms.presentation.main.ui.detail.data

data class ExpandableProjectData(
    val name: String,
    val activityDuration: String,
    val projectImage: List<String>,
    val icon: String,
    val techStack: List<String>,
    val keyTask: String,
    val relatedLinks: List<RelatedLinksData>,
    val isExpand: Boolean
)