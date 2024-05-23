package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ClassSelectorComponent(
    setClass: String,
    onClickClassOpenButton: () -> Unit,
) {
    AddGrayBody1Title(titleText = "반") {
        SmsCustomTextField(
            trailingIcon = {
                IconButton(onClick = onClickClassOpenButton) {
                    OpenButtonIcon()
                }
            },
            text = setClass,
            readOnly = true,
            placeHolder = "반을 선택해 주세요"
        )
    }
}