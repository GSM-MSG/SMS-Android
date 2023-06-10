package com.sms.presentation.main.ui.detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.sms.presentation.main.ui.fill_out_information.data.CertificationData
import com.sms.presentation.main.ui.fill_out_information.data.SchoolLifeData
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
    isNotGuest: Boolean,
    isTeacher: Boolean,
    onDreamBookButtonClick: (() -> Unit)?
) {
    val scrollState = rememberScrollState()

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
                    Text(
                        text = introduce,
                        style = typography.body2,
                        color = colors.N40,
                        modifier = itemModifier.fillMaxWidth()
                    )
                }
                if (isTeacher) {
                    // TODO (LeeHyeonbin) - 데이터 띄울 때 파라미터로 넘기기
                    StudentInfoComponent(
                        modifier = itemModifier,
                        portfolioLink = "https://litt.ly/leehyeonbin",
                        schoolLifeData = SchoolLifeData(
                            gsmAuthenticationScore = "990",
                            dreamBookFileUri = Uri.EMPTY
                        ),
                        email = "de@dlajsdlfv.doadm",
                        militaryService = "헤응",
                        workConditionData = WorkConditionData(
                            formOfEmployment = "정규직",
                            salary = "1000만원",
                            region = listOf("광주", "충칭")
                        ),
                        certificationData = CertificationData(listOf("정보처리기능사", "이잉")),
                        foreignLanguage = listOf(Pair("한국어", "쌉장인"), Pair("영어", "쫌침"))
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
