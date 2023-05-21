package com.sms.presentation.main.ui.fill_out_information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.WorkConditionComponent

@Preview
@Composable
fun WorkConditionScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TopBarComponent(text = "정보 입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

        }
        SmsSpacer()
        val workCondition = remember {
            mutableStateOf("")
        }
        WorkConditionComponent(
            wantWorkingCondition = workCondition.value,
        )
    }
}