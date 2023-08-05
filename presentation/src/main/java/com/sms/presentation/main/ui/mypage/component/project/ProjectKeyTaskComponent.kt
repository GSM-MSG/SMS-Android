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
fun ProjectKeyTaskComponent() {
    AddGrayBody1Title(titleText = "주요 작업") {
        SmsTextField(
            setText = "",
            placeHolder = "저는 해당 프로젝트에 뼈를 묻었습니다.",
            modifier = Modifier.fillMaxWidth().padding(end = 20.dp)
        ) {

        }
    }
}

@Preview
@Composable
private fun ProjectKeyTaskComponentPre() {
    ProjectKeyTaskComponent()
}