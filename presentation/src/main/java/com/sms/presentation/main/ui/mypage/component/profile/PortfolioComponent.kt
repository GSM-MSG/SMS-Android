package com.sms.presentation.main.ui.mypage.component.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.component.textfield.SmsOnlyInputTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title
import com.sms.presentation.main.ui.mypage.state.PortfolioType
import com.sms.presentation.main.ui.util.getFileNameFromUri

@Composable
fun PortfolioComponent(
    selectedPortfolioType: String,
    portfolioUrlValue: String?,
    portfolioPdfValue: Uri?,
    onClickPortfolioButton: () -> Unit,
    onPortfolioUrlValueChange: (value: String) -> Unit,
    onPortfolioPdfValueChange: (value: Uri) -> Unit
) {
    val context = LocalContext.current
    val pickPdfLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    onPortfolioPdfValueChange(uri)
                }
            }
        }

    SMSTheme { colors, typography ->
        AddGrayBody1Title(titleText = "포트폴리오 형식") {
            SmsBasicTextField(
                placeHolder = "포트폴리오 형식 선택",
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        onClickPortfolioButton()
                    }) {
                        OpenButtonIcon()
                    }
                },
                readOnly = true,
                text = selectedPortfolioType
            ) {}
        }
        when (selectedPortfolioType) {
            PortfolioType.URL.text -> {
                AddGrayBody1Title(titleText = "URL 형식") {
                    SmsOnlyInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = portfolioUrlValue ?: "",
                        placeHolder = "https://",
                        onValueChange = onPortfolioUrlValueChange
                    ) { onPortfolioUrlValueChange("") }
                }
            }

            PortfolioType.PDF.text -> {
                AddGrayBody1Title(titleText = "PDF 파일") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = colors.N10)
                            .padding(12.dp)
                            .smsClickable(rippleEnabled = false) {
                                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                                    addCategory(Intent.CATEGORY_OPENABLE)
                                    type = "application/pdf"
                                }
                                pickPdfLauncher.launch(intent)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (portfolioPdfValue != null) getFileNameFromUri(context, portfolioPdfValue) ?: ".pdf" else "+ pdf 파일 추가",
                            style = typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = if (portfolioPdfValue != null) colors.BLACK else colors.N30
                        )
                    }
                }
            }

            else -> Unit
        }
    }
}

@Preview
@Composable
private fun PortfolioComponentPre() {
    PortfolioComponent(
        selectedPortfolioType = "",
        portfolioUrlValue = "https://youtube.com",
        portfolioPdfValue = null,
        onClickPortfolioButton = {},
        onPortfolioUrlValueChange = {},
        onPortfolioPdfValueChange = {},
    )
}