package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddBody1TitleText

@Composable
fun ProjectNameInputComponent(
    text: String,
    onButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    AddBody1TitleText(titleText = "이름") {
        SmsTextField(
            setText = text,
            placeHolder = "프로젝트 이름입력",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            onClickButton = onButtonClick
        )
    }
}