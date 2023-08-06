package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectNameComponent(name: String) {
    val projectName = remember {
        mutableStateOf(name)
    }
    AddGrayBody1Title(titleText = "이름") {
        SmsTextField(
            setText = projectName.value,
            placeHolder = "예시: SMS",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            onValueChange = { projectName.value = it }) {
        }
        projectName.value = ""
    }
}

@Preview
@Composable
private fun ProjectNameComponentPre() {
    ProjectNameComponent("프로젝트 이름")
}