package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun SelfIntroduceComponent(introduceValue: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "자기 소개") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            text = introduceValue,
            placeHolder = "1줄 자기 소개 입력",
            onValueChange = onValueChange) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun SelfIntroduceComponentPre() {
    SelfIntroduceComponent("Will be king of Android", onValueChange = {})
}