package com.sms.presentation.main.ui.fill_out_information.data

import android.net.Uri

data class ProjectInfo(
    val name: String,
    val icon: Uri,
    val preview: List<Uri>,
    val keyTask: String,
    val technologyOfUse: List<String>,
    val startDate: String,
    val endDate: String,
    val relatedLinkList: List<Pair<String, String>>
)

data class ProjectsData(
    val projects: List<ProjectInfo>
)