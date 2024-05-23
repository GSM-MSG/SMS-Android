package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardTypeComponent(type: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "종류") {
        SmsTextField(
            text = type,
            placeHolder = "대상",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun AwardTypeComponentPre() {
    AwardTypeComponent(type = "제 19회 스마틴 앱 챌린지", onValueChange = {})
}