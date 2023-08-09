package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun PortfolioComponent(portfolioValue: String) {
    val portfolio = remember {
        mutableStateOf(portfolioValue)
    }
    AddGrayBody1Title(titleText = "포트폴리오 URL") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = portfolio.value,
            placeHolder = "포트폴리오 URL",
            onValueChange = { portfolio.value = it }) {
            portfolio.value = ""
        }
    }
}

@Preview
@Composable
private fun PortfolioComponentPre() {
    PortfolioComponent("https://youtube.com")
}