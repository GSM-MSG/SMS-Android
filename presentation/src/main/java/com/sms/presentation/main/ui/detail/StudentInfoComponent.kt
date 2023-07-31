package com.sms.presentation.main.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.divider.SmsDivider
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.response.CertificationModel
import com.sms.presentation.main.ui.fill_out_information.data.CertificationData
import com.sms.presentation.main.ui.fill_out_information.data.WorkConditionData

@Composable
fun StudentInfoComponent(
    modifier: Modifier,
    portfolioLink: String,
    gsmAuthenticationScore: String,
    email: String,
    militaryService: String,
    workConditionData: WorkConditionData,
    certificationData: CertificationData,
    foreignLanguage: List<CertificationModel>,
) {
    val context = LocalContext.current
    val titleTextModifier = Modifier
        .fillMaxWidth(0.4f)
        .padding(vertical = 8.dp)
    val contentTextModifier = Modifier
        .fillMaxWidth(0.6f)
        .padding(8.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        SMSTheme { colors, typography ->

            val titleColor = colors.BLACK
            val contentColor = colors.N40

            val titleTypography = typography.body1
            val contentTypography = typography.body2

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
                    text = workConditionData.region.joinToString(", "),
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
            if (certificationData.certification.isNotEmpty()) {
                SmsDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "자격증",
                        modifier = titleTextModifier,
                        color = titleColor,
                        style = titleTypography
                    )
                    Column {
                        certificationData.certification.forEach {
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
            Spacer(modifier = Modifier.height(44.dp))
            SmsRoundedButton(
                text = "포트폴리오", modifier = Modifier
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