package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.state.ActivityDuration

@Composable
fun ProjectComponent(data: ProjectData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            ProjectInfoTopBar(
                projectName = data.name,
                projectIconUrl = data.icon,
                projectDate = data.activityDuration
            )
            if (data.projectImage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                ProjectPreviewComponent(
                    projectName = data.name,
                    projectPreviewUrlList = data.projectImage
                )
            }
        }
        ProjectTechStacksComponent(
            techStackList = data.techStacks
        )
        if (data.description.isNotEmpty()) {
            ProjectDescriptionComponent(
                description = data.description
            )
        }
        if (data.keyTask.isNotEmpty()) {
            ProjectKeyTaskComponent(
                keyTask = data.keyTask
            )
        }
        if (data.relatedLinks.isNotEmpty()) {
            ProjectRelatedLinksComponent(
                links = data.relatedLinks
            )
        }
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectComponent(
        data = ProjectData(
            name = "SMS",
            activityDuration = ActivityDuration(start = "2023. 03", end = null),
            projectImage = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
            icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            techStacks = listOf(
                "Github",
                "Git",
                "Kotlin",
                "Android Studio",
                "Kotlin",
                "Android Studio",
                "Kotlin",
                "Android Studio"
            ),
            keyTask = "모이자 ㅋㅋ",
            relatedLinks = listOf(
                RelatedLinksData("Youtube", "https://dolmc.com"),
                RelatedLinksData("GitHujb", "https://youyu.com"),
                RelatedLinksData("X", "https://asdgasgw.com")
            ),
            description = ""
        )
    )
}