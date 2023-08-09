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
fun SelfIntroduceComponent(introduceValue: String) {
    AddGrayBody1Title(titleText = "자기 소개") {
        val introduce = remember {
            mutableStateOf(introduceValue)
        }
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = introduce.value,
            placeHolder = "1줄 자기 소개 입력",
            onValueChange = {
                introduce.value = it
            }) {
            introduce.value = ""
        }
    }
}

@Preview
@Composable
private fun SelfIntroduceComponentPre() {
    SelfIntroduceComponent("Will be king of Android")
}