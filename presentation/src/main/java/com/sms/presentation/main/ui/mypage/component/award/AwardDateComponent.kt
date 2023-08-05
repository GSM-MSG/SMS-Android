package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardDateComponent() {
    AddGrayBody1Title(titleText = "기간") {
        SmsCustomTextField(
            endIcon = { CalendarIcon() },
            clickAction = { },
            setChangeText = "",
            placeHolder = "2023.09"
        )
    }
}

@Preview
@Composable
private fun AwardDateComponentPre() {
    AwardDateComponent()
}