package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardDateBarComponent(
    date: String,
    onClick: () -> Unit
) {
    AddGrayBody1Title(titleText = "기간") {
        SmsCustomTextField(
            setChangeText = date,
            placeHolder = "yyyy.mm",
            readOnly = true,
            endIcon = { CalendarIcon() },
            clickAction = onClick
        )
    }
}