package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsOnlyInputTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun EmailComponent(emailValue: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "이메일") {
        SmsOnlyInputTextField(
            modifier = Modifier.fillMaxWidth(),
            text = emailValue,
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