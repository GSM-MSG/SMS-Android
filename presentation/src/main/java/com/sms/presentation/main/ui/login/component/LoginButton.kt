package com.sms.presentation.main.ui.login.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types

@Composable
fun LoginButton(onClick: () -> Unit) {
    GAuthButton(
        style = Types.Style.ROUNDED,
        actionType = Types.ActionType.SIGNIN,
        colors = Types.Colors.WHITE,
        horizontalMargin = 20.dp
    ) {
        onClick()
    }
}
