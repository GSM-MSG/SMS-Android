package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton

@Composable
fun ProjectsBottomButtonComponent(
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        SmsRoundedButton(
            text = "이전", modifier = Modifier.weight(1f),
            state = ButtonState.OutLine,
            onClick = onPreviousButtonClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        SmsRoundedButton(
            text = "다음", modifier = Modifier.weight(2.25f),
            onClick = onNextButtonClick
        )
    }
}