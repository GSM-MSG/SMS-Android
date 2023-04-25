package com.sms.presentation.main.ui.login.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(onClick: () -> Unit) {
    Box {
        LoginPageBackGround()
        TopComponent()
        LoginButton {
            onClick()
        }
    }
}