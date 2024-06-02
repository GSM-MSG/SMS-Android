package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsOnlyInputTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectDescriptionComponent(projectDescription: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "프로젝트 설명") {
        SmsOnlyInputTextField(
            text = projectDescription,
            placeHolder = "프젝로트 내용 서술",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            onValueChange = onValueChange
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun ProjectDescriptionComponentPre() {
    ProjectDescriptionComponent(projectDescription = "이잉", onValueChange = {})
}