package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun EmailComponent() {
    AddGrayBody1Title(titleText = "이메일") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = "",
            placeHolder = "이메일",
            onValueChange = {}) {

        }
    }
}

@Preview
@Composable
private fun EmailComponentPre() {
    EmailComponent()
}