package com.sms.presentation.main.ui.fill_out_information.data

import android.net.Uri

data class ProjectData(
    val name: String,
    val icon: Uri,
    val preview: List<Uri>,
    val technologyOfUse: List<String>,
    val startDate: String,
    val endDate: String,
    val relatedLinkList: List<Pair<String, String>>
)
