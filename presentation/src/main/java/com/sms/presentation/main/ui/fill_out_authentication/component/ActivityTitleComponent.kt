package com.sms.presentation.main.ui.fill_out_authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ActivityTitleComponent(
    activityTitleValue: String,
    onValueChange: (value: String) -> Unit
) {
    AddGrayBody1Title(titleText = "활동 제목") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            text = activityTitleValue,
            placeHolder = "활동 제목 입력",
            onValueChange = onValueChange
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun ActivityTitleComponentPre() {
    ActivityTitleComponent("Droid Knights 2024", onValueChange = {})
}