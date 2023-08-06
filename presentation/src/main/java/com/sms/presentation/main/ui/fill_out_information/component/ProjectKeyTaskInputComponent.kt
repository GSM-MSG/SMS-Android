package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectKeyTaskInputComponent(
    text: String,
    onButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    AddGrayBody1Title(titleText = "주요 작업") {
        SmsTextField(
            setText = text,
            placeHolder = "주요 작업 내용서술",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            onClickButton = onButtonClick
        )
    }
}