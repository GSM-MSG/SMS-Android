package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardDateBarComponent(
    date: String,
    isDateEmpty: Boolean,
    onFocusRequested: Boolean,
    onClick: () -> Unit
) {
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    LaunchedEffect(onFocusRequested) {
        if (onFocusRequested) {
            focusRequester.captureFocus()
        }
    }

    AddGrayBody1Title(titleText = "기간") {
        SmsCustomTextField(
            setChangeText = date,
            placeHolder = "yyyy.mm",
            readOnly = true,
            endIcon = { CalendarIcon() },
            clickAction = onClick,
            errorText = "수상 일자를 입력해 주세요.",
            isError = isDateEmpty,
            focusRequester = focusRequester
        )
    }
}