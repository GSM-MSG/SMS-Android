package com.sms.presentation.main.ui.fill_out_authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ActivityDescriptionComponent(
    modifier: Modifier,
    activityDescriptionValue: String,
    onValueChange: (value: String) -> Unit
) {
    AddGrayBody1Title(titleText = "활동 설명") {
        SmsTextField(
            modifier = modifier.fillMaxWidth(),
            setText = activityDescriptionValue,
            placeHolder = "활동 설명 입력",
            onValueChange = onValueChange
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun ActivityDescriptionComponentPre() {
    ActivityDescriptionComponent(Modifier,"Droid Knights 2024", onValueChange = {})
}