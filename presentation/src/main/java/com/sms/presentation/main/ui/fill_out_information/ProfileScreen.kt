package com.sms.presentation.main.ui.fill_out_information

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.ProfileComponent

@Preview
@Composable
fun ProfileScreen() {
    Column {
        TopBarComponent(
            text = "정보입력",
            leftIcon = { BackButtonIcon() },
            rightIcon = null,
            onClickLeftButton = {

            }) {
        }
        SmsSpacer()
        ProfileComponent()
    }
}