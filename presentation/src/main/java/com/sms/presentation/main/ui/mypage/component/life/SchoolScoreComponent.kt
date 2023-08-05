package com.sms.presentation.main.ui.mypage.component.life

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun SchoolScoreComponent() {
    AddGrayBody1Title(titleText = "인증제 점수") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = "",
            placeHolder = "몇 점이고! 인증제 점수말이다.",
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        ) {

        }
    }
}

@Preview
@Composable
private fun SchoolScoreComponentPre() {
    SchoolScoreComponent()
}