package com.sms.presentation.main.ui.fill_out_information.data

import android.net.Uri

data class ProjectInfo(
    val name: String = "",
    val icon: Uri = Uri.EMPTY,
    val preview: List<Uri> = emptyList(),
    val technologyOfUse: List<String> = emptyList(),
    val description: String = "",
    val keyTask: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val relatedLinkList: List<Pair<String, String>> = listOf(Pair("", "")),
    val isProjectProgress: Boolean = false,
    val isToggleOpen: Boolean = false
)

data class ProjectsData(
    val projects: List<ProjectInfo>
)