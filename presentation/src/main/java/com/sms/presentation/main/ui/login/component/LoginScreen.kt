package com.sms.presentation.main.ui.login.component

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(context: Context, onClick: () -> Unit) {
    Box {
        LoginPageBackGround()
        TopComponent(context)
        LoginButton {
            onClick()
        }
    }
}