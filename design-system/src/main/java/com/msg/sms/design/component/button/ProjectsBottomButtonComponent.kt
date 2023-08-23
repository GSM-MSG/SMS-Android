package com.msg.sms.design.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackAndNextButtonComponent(
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SmsRoundedButton(
            text = "이전", modifier = Modifier.weight(1f),
            state = ButtonState.OutLine,
            onClick = onPreviousButtonClick
        )
        SmsRoundedButton(
            text = "다음", modifier = Modifier.weight(2.25f),
            onClick = onNextButtonClick
        )
    }
}