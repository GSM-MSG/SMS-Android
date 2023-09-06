package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardTypeInputComponent(
    title: String,
    placeHolder: String,
    text: String,
    isTypeEmpty: Boolean,
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
            singleLine = true,
            errorText = "수상 종류를 입력해 주세요.",
            isError = isTypeEmpty
        )
    }
}