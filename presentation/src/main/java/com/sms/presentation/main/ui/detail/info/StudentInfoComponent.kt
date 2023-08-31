package com.sms.presentation.main.ui.detail.info

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.divider.SmsDivider
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.common.CertificateModel
import com.sms.presentation.main.ui.fill_out_information.data.WorkConditionData

@Composable
fun StudentInfoComponent(
    modifier: Modifier = Modifier,
    gsmAuthenticationScore: String,
    email: String,
    militaryService: String,
    workConditionData: WorkConditionData,
    certificationData: List<String>,
    foreignLanguage: List<CertificateModel>,
) {
    val titleTextModifier = Modifier
        .fillMaxWidth(0.4f)
        .padding(vertical = 8.dp)
    val contentTextModifier = Modifier
        .fillMaxWidth(0.6f)
        .padding(8.dp)

    Column(modifier = modifier.fillMaxWidth()) {
        SMSTheme { colors, typography ->
            val titleColor = colors.BLACK
            val contentColor = colors.N40

            val titleTypography = typography.body1
            val contentTypography = typography.body2

            Text(
                text = "세부정보",
                style = typography.title2,
                color = colors.BLACK,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "이메일",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = email,
                    modifier = contentTextModifier,
                    color = contentColor,
                    style = contentTypography
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "인증제점수",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = gsmAuthenticationScore,
                    modifier = contentTextModifier,
                    color = contentColor,
                    style = contentTypography
                )
            }
            SmsDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "병특 희망 여부",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = militaryService,
                    modifier = contentTextModifier,
                    color = contentColor,
                    style = contentTypography
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "희망 고용 형태",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = workConditionData.formOfEmployment,
                    modifier = contentTextModifier,
                    color = contentColor,
                    style = contentTypography
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "희망 연봉",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = workConditionData.salary,
                    modifier = contentTextModifier,
                    color = contentColor,
                    style = contentTypography
                )
            }
            SmsDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "근무 희망 지역",
                    modifier = titleTextModifier,
                    color = titleColor,
                    style = titleTypography
                )
                Text(
                    text = workConditionData.regions.joinToString(", "),
                    modifier = contentTextModifier, color = contentColor, style = contentTypography
                )
            }
            if (foreignLanguage.isNotEmpty()) {
                SmsDivider(modifier = Modifier.padding(vertical = 8.dp))
                Column(Modifier.fillMaxWidth()) {
                    foreignLanguage.forEach {
                        Row {
                            Text(
                                text = it.languageCertificateName,
                                modifier = titleTextModifier,
                                color = titleColor,
                                style = titleTypography
                            )
                            Text(
                                text = it.score,
                                modifier = contentTextModifier,
                                color = contentColor,
                                style = contentTypography
                            )
                        }
                    }
                }
            }
            if (certificationData.isNotEmpty()) {
                SmsDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "자격증",
                        modifier = titleTextModifier,
                        color = titleColor,
                        style = titleTypography
                    )
                    Column {
                        certificationData.forEach {
                            Text(
                                text = it,
                                modifier = contentTextModifier,
                                color = contentColor,
                                style = contentTypography
                            )
                        }
                    }
                }
            }
        }
    }
}