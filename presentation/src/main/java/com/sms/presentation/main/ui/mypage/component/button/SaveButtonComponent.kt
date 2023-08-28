package com.sms.presentation.main.ui.mypage.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton

@Composable
fun SaveButtonComponent(
    modifier: Modifier = Modifier,
    visibility: Boolean,
    enabled: Boolean = true,
    onClickSaveButton: () -> Unit,
) {
    AnimatedVisibility(modifier = modifier.padding(bottom = 20.dp), visible = visibility) {
        SmsRoundedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "저장",
            onClick = onClickSaveButton,
            enabled = enabled
        )
    }
}

@Preview
@Composable
private fun SaveButtonComponentPre() {
    SaveButtonComponent(visibility = true, onClickSaveButton = {})
}