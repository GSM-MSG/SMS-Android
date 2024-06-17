package com.sms.presentation.main.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.common.ProjectModel
import com.sms.presentation.main.ui.detail.award.AwardComponent
import com.sms.presentation.main.ui.detail.data.WorkConditionData
import com.sms.presentation.main.ui.detail.dialog.CopyLinkDialog
import com.sms.presentation.main.ui.detail.dialog.SelectExpirationDateDialog
import com.sms.presentation.main.ui.detail.info.StudentInfoComponent
import com.sms.presentation.main.ui.detail.profile.StudentProfileComponent
import com.sms.presentation.main.ui.detail.project.ProjectListComponent
import com.sms.presentation.main.ui.util.stringDaysDataToLongDaysData
import com.sms.presentation.main.viewmodel.StudentListViewModel

enum class ExpirationDate(val date: String) {
    DAYS_5("5일"),
    DAYS_10("10일"),
    DAYS_15("15일"),
    DAYS_20("20일"),
    DAYS_25("25일"),
    DAYS_30("30일")
}

@Composable
fun StudentDetailComponent(
    imageHeight: Dp,
    major: String,
    modifier: Modifier,
    name: String,
    techStack: List<String>,
    grade: Int?,
    classNumber: Int?,
    schoolNumber: Int?,
    departments: String,
    introduce: String,
    portfolioLink: String,
    awardData: List<PrizeModel>,
    projectList: List<ProjectModel>,
    gsmAuthenticationScore: String,
    email: String,
    militaryService: String,
    formOfEmployment: String,
    salary: String,
    region: List<String>,
    certificationData: List<String>,
    foreignLanguage: List<CertificateModel>,
    isNotGuest: Boolean,
    isTeacher: Boolean,
    scrollState: ScrollState = rememberScrollState(),
    viewModel: StudentListViewModel
) {
    val context = LocalContext.current

    var onClickSharingButtonState = remember {
        mutableStateOf(false)
    }

    val successCreateLinkStatus by viewModel.createInformationLinkState.collectAsState()
    val createdLinkToken by viewModel.createInformationLinkResponse.collectAsState()


    if (onClickSharingButtonState.value) {
        SelectExpirationDateDialog(
            title = "만료기간 선택",
            outLineButtonText = "취소",
            importantButtonText = "링크 생성",
            outlineButtonOnClick = { onClickSharingButtonState.value = false },
            importantButtonOnClick = {
                viewModel.createInformationLink(
                    viewModel.studentId.value.toString(),
                    viewModel.selectedExpirationDaysData.value.stringDaysDataToLongDaysData()
                )
                onClickSharingButtonState.value = false
            },
            widthDp = 328.dp,
            heightDp = 280.dp,
            expirationDate = listOf(
                ExpirationDate.DAYS_5.date,
                ExpirationDate.DAYS_10.date,
                ExpirationDate.DAYS_15.date,
                ExpirationDate.DAYS_20.date,
                ExpirationDate.DAYS_25.date,
                ExpirationDate.DAYS_30.date
            ),
            onSelectedExpiredDays = {
                viewModel.selectedExpirationDaysDataChange(it)
            }
        )
    }
    
    if (successCreateLinkStatus) {
        CopyLinkDialog(
            title = "만료기간 선택",
            outLineButtonText = "",
            importantButtonText = "확인",
            outlineButtonOnClick = { },
            importantButtonOnClick = {
                viewModel.saveCreateInformationLinkState(false)
                                     },
            token = createdLinkToken,
            widthDp = 328.dp,
            heightDp = 226.dp,
        )
    }


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
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(Color.White)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                StudentProfileComponent(
                    major = major,
                    name = name,
                    isNotGuest = isNotGuest,
                    techStack = techStack,
                    grade = grade,
                    classNumber = classNumber,
                    schoolNumber = schoolNumber,
                    departments = departments,
                    introduce = introduce
                )
                if (isTeacher) {
                    StudentInfoComponent(
                        gsmAuthenticationScore = gsmAuthenticationScore,
                        email = email,
                        militaryService = militaryService,
                        workConditionData = WorkConditionData(
                            formOfEmployment = formOfEmployment,
                            salary = salary,
                            regions = region
                        ),
                        certificationData = certificationData,
                        foreignLanguage = foreignLanguage
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    if (awardData.isNotEmpty()) {
                        AwardComponent(awardList = awardData)
                    }
                    if (projectList.isNotEmpty()) {
                        ProjectListComponent(projectList = projectList)
                    }
                    if (isTeacher) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        ) {
                            SmsRoundedButton(
                                text = "포트폴리오",
                                modifier = Modifier
                                    .fillMaxWidth(0.685f)
                            ) {
                                val urlIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(portfolioLink)
                                )
                                context.startActivity(urlIntent)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            SmsRoundedButton(
                                text = "공유",
                                modifier = Modifier
                                    .fillMaxWidth(0.95f),
                                state = ButtonState.OutLine
                            ) {
                                onClickSharingButtonState.value = true
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
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
        grade = 3,
        classNumber = 2,
        schoolNumber = 15,
        departments = "SW개발과",
        introduce = "트렌드가 문화가 될 때까지",
        portfolioLink = "https://",
        awardData = listOf(PrizeModel("기업상", type = "해피문데이", date = "2023. 08. 09")),
        projectList = listOf(
            ProjectModel(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023. 03", end = null),
                previewImages = listOf(
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
                ),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStacks = listOf("Github", "Git", "Kotlin", "Android Studio"),
                task = "모이자 ㅋㅋ",
                links = listOf(
                    LinkModel("Youtube", "https://dolmc.com"),
                    LinkModel("GitHujb", "https://youyu.com"),
                    LinkModel("X", "https://asdgasgw.com")
                ),
                description = ""
            ),
            ProjectModel(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023.03", end = null),
                previewImages = listOf("https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStacks = listOf(
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
                task = "모이자 ㅋㅋ",
                links = listOf(
                    LinkModel("Youtube", "https://dolmc.com"),
                    LinkModel("GitHujb", "https://youyu.com"),
                    LinkModel("X", "https://asdgasgw.com")
                ),
                description = ""
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
        viewModel =
    )
}
*/
