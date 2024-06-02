package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsOnlyInputTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun PortfolioComponent(portfolioValue: String?, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "포트폴리오 URL") {
        SmsOnlyInputTextField(
            modifier = Modifier.fillMaxWidth(),
            text = portfolioValue?:"",
            placeHolder = "포트폴리오 URL",
            onValueChange = onValueChange) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun PortfolioComponentPre() {
    PortfolioComponent("https://youtube.com", onValueChange = {})
}