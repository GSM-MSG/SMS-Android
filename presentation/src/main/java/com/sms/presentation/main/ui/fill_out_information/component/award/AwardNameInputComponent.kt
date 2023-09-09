package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardNameInputComponent(
    title: String,
    placeHolder: String,
    text: String,
    isNameEmpty: Boolean,
    onFocusRequested: Boolean,
    onButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    LaunchedEffect(onFocusRequested) {
        if (onFocusRequested) {
            focusRequester.requestFocus()
        }
    }

    AddGrayBody1Title(titleText = title) {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = text,
            placeHolder = placeHolder,
            onValueChange = onValueChange,
            onClickButton = onButtonClick,
            singleLine = true,
            errorText = "수상 이름을 입력해 주세요.",
            isError = isNameEmpty,
            focusRequester = focusRequester
        )
    }
}