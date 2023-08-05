package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProjectComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        ProjectNameComponent()
        ProjectIconComponent()
        ProjectPreviewComponent(
            list = listOf(
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
            )
        )
        ProjectTechStackComponent(
            techStack = listOf(
                "Kotlin",
                "Android Studio",
                "git",
                "Github",
                "Jetpack Compose"
            )
        )
        ProjectKeyTaskComponent()
        ProjectScheduleComponent()
        ProjectRelatedLinksComponent(
            relatedLinks = listOf(
                Pair("Github", "https://github/leehyeonbin.com"),
                Pair("Youtube", "https://youtube.com")
            )
        )
        ProjectDivider()
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectComponent()
}