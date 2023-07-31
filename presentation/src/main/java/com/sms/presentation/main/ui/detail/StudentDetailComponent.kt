package com.sms.presentation.main.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.item.TechStackItem
import com.msg.sms.design.icon.BookIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.response.CertificationModel
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
    onDreamBookButtonClick: (() -> Unit)?,
    scrollState: ScrollState
) {
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
                    .clip(RoundedCornerShape(24.dp))
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
                        portfolioLink = portfolioLink,
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
            }
            if (isTeacher) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 20.dp, end = 20.dp),
                    onClick = onDreamBookButtonClick!!
                ) {
                    BookIcon()
                }
            }
        }
    }
}
