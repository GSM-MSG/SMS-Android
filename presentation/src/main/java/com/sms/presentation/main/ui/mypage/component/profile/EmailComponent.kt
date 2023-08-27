package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun EmailComponent(emailValue: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "이메일") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = emailValue,
            placeHolder = "이메일",
            onValueChange = onValueChange) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun EmailComponentPre() {
    EmailComponent("s21042@gsm.hs.kr", onValueChange = {})
}