package com.sms.presentation.main.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.item.TechStackItem
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.response.CertificationModel
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.detail.project.ProjectListComponent
import com.sms.presentation.main.ui.fill_out_information.data.CertificationData
import com.sms.presentation.main.ui.fill_out_information.data.WorkConditionData

@Composable
fun StudentDetailComponent(
    imageHeight: Dp,
    major: String,
    modifier: Modifier,
    name: String,
    techStack: List<String>,
    grade: String,
    classNumber: String,
    schoolNumber: String,
    departments: String,
    introduce: String,
    portfolioLink: String,
    awardData: List<AwardData>,
    projectList: List<ProjectData>,
    gsmAuthenticationScore: String,
    email: String,
    militaryService: String,
    formOfEmployment: String,
    salary: String,
    region: List<String>,
    certificationData: List<String>,
    foreignLanguage: List<CertificationModel>,
    isNotGuest: Boolean,
    isTeacher: Boolean,
    scrollState: ScrollState = rememberScrollState(),
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        Spacer(
            modifier = Modifier
                .height(imageHeight)
                .background(Color.Transparent)
        )
        Box {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(Color.White)
            ) {
                val itemModifier = Modifier.padding(horizontal = 20.dp)

                Spacer(modifier = Modifier.height(16.dp))

                SMSTheme { colors, typography ->
                    Text(
                        text = major,
                        style = typography.body1,
                        color = colors.S2,
                        fontWeight = FontWeight.Normal,
                        modifier = itemModifier
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = name,
                        style = typography.headline3,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold,
                        modifier = itemModifier
                    )
                    if (isNotGuest) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${grade}학년 ${classNumber}반 ${schoolNumber}번 • $departments",
                            style = typography.body2,
                            color = colors.N40,
                            modifier = itemModifier
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = itemModifier
                    ) {
                        items(techStack) {
                            TechStackItem(techStack = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = itemModifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.N10)
                    ) {
                        Text(
                            text = "자기소개",
                            style = typography.caption2,
                            color = colors.N40,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = introduce,
                            style = typography.body2,
                            color = colors.BLACK,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                }
                if (isTeacher) {
                    StudentInfoComponent(
                        modifier = itemModifier,
                        gsmAuthenticationScore = gsmAuthenticationScore,
                        email = email,
                        militaryService = militaryService,
                        workConditionData = WorkConditionData(
                            formOfEmployment = formOfEmployment,
                            salary = salary,
                            region = region
                        ),
                        certificationData = CertificationData(certificationData),
                        foreignLanguage = foreignLanguage
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    AwardComponent(awardList = awardData)
                    ProjectListComponent(projectList = projectList)
                    SmsRoundedButton(
                        text = "포트폴리오",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(portfolioLink)
                        )
                        context.startActivity(urlIntent)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun StudentDetailComponentPre() {
    StudentDetailComponent(
        imageHeight = 300.dp,
        major = "Android",
        modifier = Modifier.heightIn(max = 1000.dp),
        name = "이현빈",
        techStack = listOf(
            "Compose UI",
            "Android Studio",
            "Kotlin",
            "Flutter",
            "Dart",
            "Compose for Web"
        ),
        grade = "3",
        classNumber = "2",
        schoolNumber = "15",
        departments = "SW개발과",
        introduce = "트렌드가 문화가 될 때까지",
        portfolioLink = "https://",
        awardData = listOf(AwardData("기업상", organization = "해피문데이", date = "2023. 08. 09")),
        projectList = listOf(
            ProjectData(
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
            ),
            ProjectData(
                name = "SMS",
                activityDuration = "2023 ~",
                projectImage = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStack = listOf(
                    "Github",
                    "Git",
                    "Kotlin",
                    "Android Studio",
                    "Github",
                    "Git",
                    "Kotlin",
                    "Android Studio",
                    "Github",
                    "Git",
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
        ),
        gsmAuthenticationScore = "800",
        email = "dev.leehyeonbin@gmail.com",
        militaryService = "힝",
        formOfEmployment = "정직원",
        salary = "4000",
        region = listOf("서울"),
        certificationData = listOf(),
        foreignLanguage = listOf(),
        isNotGuest = true,
        isTeacher = true,
    )
}
