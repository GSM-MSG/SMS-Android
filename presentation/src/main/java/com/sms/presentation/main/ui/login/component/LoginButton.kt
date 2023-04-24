package com.sms.presentation.main.ui.login.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types

@Composable
fun LoginButton(dpWidth: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GAuthButton(
            style = Types.Style.ROUNDED,
            actionType = Types.ActionType.SIGNIN,
            colors = Types.Colors.WHITE,
            horizontalPaddingValue = (dpWidth / 2 - 450).dp
        ) {
            onClick()
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    }
}
