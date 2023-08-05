package com.sms.presentation.main.ui.mypage.component.military

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun MilitaryServiceTypeComponent() {
    AddGrayBody1Title(titleText = "병특 희망 여부") {
        SmsCustomTextField(endIcon = { OpenButtonIcon() }, clickAction = { }, setChangeText = "", placeHolder = "병특 희망")
    }
}

@Preview
@Composable
fun MilitaryServiceTypeComponentPre() {
    MilitaryServiceTypeComponent()
}