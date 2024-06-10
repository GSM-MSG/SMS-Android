package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.domain.model.common.LinkModel
import com.msg.sms.domain.model.common.ProjectModel
import com.msg.sms.domain.model.user.response.ActivityDuration

@Composable
fun ProjectComponent(data: ProjectModel) {
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
            if (data.previewImages.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                ProjectPreviewComponent(
                    projectName = data.name,
                    projectPreviewUrlList = data.previewImages
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
        if (data.task.isNotEmpty()) {
            ProjectKeyTaskComponent(
                keyTask = data.task
            )
        }
        if (data.links.isNotEmpty()) {
            ProjectRelatedLinksComponent(
                links = data.links
            )
        }
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectComponent(
        data = ProjectModel(
            name = "SMS",
            activityDuration = ActivityDuration(start = "2023. 03", end = null),
            previewImages = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
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
            task = "모이자 ㅋㅋ",
            links = listOf(
                LinkModel("Youtube", "https://dolmc.com"),
                LinkModel("GitHujb", "https://youyu.com"),
                LinkModel("X", "https://asdgasgw.com")
            ),
            description = ""
        )
    )
}