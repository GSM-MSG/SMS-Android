package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun SelfIntroduceComponent() {
    AddGrayBody1Title(titleText = "자기소개") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = "",
            placeHolder = "1줄 자기소개 입력",
            onValueChange = {}) {

        }
    }
}

@Preview
@Composable
private fun SelfIntroduceComponentPre() {
    SelfIntroduceComponent()
}