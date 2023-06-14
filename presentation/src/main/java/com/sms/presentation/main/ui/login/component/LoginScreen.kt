package com.sms.presentation.main.ui.login.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    context: Context,
    onLoginButtonClick: () -> Unit,
    onLookAroundToGuestButtonClick: () -> Unit,
) {
    Box {
        LoginPageBackGround()
        TopComponent(context)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginButton {
                onLoginButtonClick()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                LookAroundToGuestButton {
                    onLookAroundToGuestButtonClick()
                }
                Spacer(modifier = Modifier.width(16.dp))
                ShowTheTermsOfUse(context = context)
            }
            Spacer(modifier = Modifier.height(73.dp))
        }
    }
}