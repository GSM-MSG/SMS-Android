package com.sms.presentation.main.ui.fill_out_information.data

import android.net.Uri

data class ProjectInfo(
    val name: String = "",
    val icon: Uri = Uri.EMPTY,
    val preview: List<Uri> = emptyList(),
    val keyTask: String = "",
    val technologyOfUse: List<String> = emptyList(),
    val startDate: String = "",
    val endDate: String = "",
    val relatedLinkList: List<Pair<String, String>> = listOf(Pair("", ""))
)

data class ProjectsData(
    val projects: List<ProjectInfo>
)