package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectNameComponent() {
    AddGrayBody1Title(titleText = "이름") {
        SmsTextField(setText = "", placeHolder = "예시: SMS", modifier = Modifier.fillMaxWidth().padding(end = 20.dp)) {

        }

    }
}

@Preview
@Composable
private fun ProjectNameComponentPre() {
    ProjectNameComponent()
}