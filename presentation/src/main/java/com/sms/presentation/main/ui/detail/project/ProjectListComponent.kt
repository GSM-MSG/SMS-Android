package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.state.ActivityDuration

@Composable
fun ProjectListComponent(
    projectList: List<ProjectData>,
) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "프로젝트",
                style = typography.title2,
                color = colors.BLACK,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .heightIn(max = 10000.dp),
                contentPadding = PaddingValues(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(projectList.size) {
                    ProjectComponent(data = projectList[it])
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectListComponent(
        projectList = listOf(
            ProjectData(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023. 03", end = null),
                projectImage = listOf(
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
                ),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStacks = listOf("Github", "Git", "Kotlin", "Android Studio"),
                keyTask = "모이자 ㅋㅋ",
                relatedLinks = listOf(
                    RelatedLinksData("Youtube", "https://dolmc.com"),
                    RelatedLinksData("GitHujb", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                ),
                description = ""
            ),
            ProjectData(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023. 03", end = "2023. 07"),
                projectImage = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStacks = listOf("Github", "Git", "Kotlin", "Android Studio"),
                keyTask = "모이자 ㅋㅋ",
                relatedLinks = listOf(
                    RelatedLinksData("Youtube", "https://dolmc.com"),
                    RelatedLinksData("GitHujb", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                ), description = "ø"
            )
        )
    )
}