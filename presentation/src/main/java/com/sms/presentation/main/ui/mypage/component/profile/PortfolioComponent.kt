package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun PortfolioComponent() {
    AddGrayBody1Title(titleText = "포트폴리오 URL") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = "",
            placeHolder = "포트폴리오 URL",
            onValueChange = {}) {

        }
    }
}

@Preview
@Composable
private fun PortfolioComponentPre() {
    PortfolioComponent()
}