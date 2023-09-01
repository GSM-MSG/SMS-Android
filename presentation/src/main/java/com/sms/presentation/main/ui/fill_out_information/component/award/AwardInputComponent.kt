package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardNameInputComponent(
    title: String,
    placeHolder: String,
    text: String,
    onButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    AddGrayBody1Title(titleText = title) {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = text,
            placeHolder = placeHolder,
            onValueChange = onValueChange,
            onClickButton = onButtonClick,
            singleLine = true
        )
    }
}