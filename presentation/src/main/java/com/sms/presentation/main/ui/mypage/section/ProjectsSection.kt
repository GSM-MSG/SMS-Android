package com.sms.presentation.main.ui.mypage.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.component.project.ProjectComponent

@Composable
fun ProjectsSection(data: ProjectData, onNameValueChange: (value: String) -> Unit) {
    ProjectComponent(data = data, onNameValueChange = onNameValueChange)
}

@Preview
@Composable
private fun ProjectSectionPre() {
    ProjectsSection( ProjectData(
        name = "SMS",
        activityDuration = "2023 ~",
        projectImage = listOf(
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
        ),
        icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
        techStack = listOf("Github", "Git", "Kotlin", "Android Studio"),
        keyTask = "모이자 ㅋㅋ",
        relatedLinks = listOf(
            RelatedLinksData("Youtube", "https://dolmc.com"),
            RelatedLinksData("GitHujb", "https://youyu.com"),
            RelatedLinksData("X", "https://asdgasgw.com")
        )
    ), onNameValueChange = {})
}