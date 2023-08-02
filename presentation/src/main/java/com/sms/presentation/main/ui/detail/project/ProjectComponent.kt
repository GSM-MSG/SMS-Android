package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.text.TechStackRow
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddBody1TitleText
import com.sms.presentation.main.ui.detail.ImportantTaskComponent
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.detail.link.RelatedLinksComponent

@Composable
fun ProjectComponent(data: ProjectData) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberAsyncImagePainter(model = data.icon),
                    contentDescription = "${data.name} 아이콘",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8))
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = data.name,
                        style = typography.body1,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = data.activityDuration,
                        style = typography.caption2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                LazyVerticalGrid(
                    userScrollEnabled = false,
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxWidth + 100.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    itemsIndexed(data.projectImage) { _, item ->
                        Image(
                            painter = rememberAsyncImagePainter(model = item),
                            contentDescription = "${data.name} 이미지",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .aspectRatio(1f)
                        )
                    }
                }
            }
            AddBody1TitleText(titleText = "사용기술", spaceSize = 8) {
                TechStackRow(modifier = Modifier, techStack = data.techStack)
            }
            Spacer(modifier = Modifier.height(24.dp))
            ImportantTaskComponent(importantTask = data.keyTask)
            Spacer(modifier = Modifier.height(24.dp))
            RelatedLinksComponent(links = data.relatedLinks)
        }
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectComponent(
        data = ProjectData(
            name = "SMS",
            activityDuration = "2023 ~",
            projectImage = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
            icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            techStack = listOf(
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
            )
        )
    )
}