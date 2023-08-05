package com.sms.presentation.main.ui.mypage.component.work

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun WantPayComponent() {
    AddGrayBody1Title(titleText = "희망 연봉") {
        SmsTextField(
            setText = "",
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "지금 내 통장엔 억억억억억억",
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        ) {

        }
    }
}

@Preview
@Composable
private fun WantPayComponentPre() {
    WantPayComponent()
}