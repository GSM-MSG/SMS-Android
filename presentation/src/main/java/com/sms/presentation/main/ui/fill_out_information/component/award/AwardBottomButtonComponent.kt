package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton

@Composable
fun AwardBottomButtonComponent(
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SmsRoundedButton(
            text = "이전", modifier = Modifier.weight(1f),
            state = ButtonState.OutLine,
            onClick = onBackButtonClick
        )
        SmsRoundedButton(
            text = "완료", modifier = Modifier.weight(2.25f),
            onClick = onNextButtonClick
        )
    }
}