package com.sms.presentation.main.ui.mypage.component.military

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun MilitaryServiceTypeComponent(
    setMilitary: String,
    onClickMilitaryOpenButton: () -> Unit,
) {
    AddGrayBody1Title(titleText = "병특 희망 여부") {
        SmsCustomTextField(
            trailingIcon = {
                IconButton(onClick = onClickMilitaryOpenButton) {
                    OpenButtonIcon()
                }
            },
            text = setMilitary,
            readOnly = true,
            placeHolder = "병특 희망"
        )
    }
}

@Preview
@Composable
fun MilitaryServiceTypeComponentPre() {
    MilitaryServiceTypeComponent("병특 희망") {}
}