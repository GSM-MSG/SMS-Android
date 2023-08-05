package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardNameComponent() {
    AddGrayBody1Title(titleText = "이름") {
        SmsTextField(setText = "", placeHolder = "제 19회 스마틴 앱 챌린지 대상") {

        }
    }
}

@Preview
@Composable
private fun AwardNameComponentPre() {
    AwardNameComponent()
}