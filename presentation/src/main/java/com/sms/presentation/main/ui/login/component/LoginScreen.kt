package com.sms.presentation.main.ui.login.component

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.gauthsignin.GAuthSigninWebView
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.BuildConfig
import com.sms.presentation.R
import com.sms.presentation.main.ui.util.isBrowserInstalled

@Composable
fun LoginScreen(
    context: Context,
    loginCallBack: (code: String) -> Unit,
    onLookAroundToGuestButtonClick: () -> Unit,
) {
    val webViewVisible = remember {
        mutableStateOf(false)
    }

    val dialogState = remember {
        mutableStateOf(false)
    }

    if (dialogState.value) {
        SmsDialog(
            widthPercent = 1f,
            heightPercent = 0.54f,
            title = context.getString(R.string.browser_error_title),
            msg = context.getString(R.string.browser_error_content),
            outLineButtonText = context.getString(R.string.cansel),
            importantButtonText = context.getString(R.string.check),
            outlineButtonOnClick = { dialogState.value = false },
            importantButtonOnClick = { dialogState.value = false }
        )
    }

    Box {
        LoginPageBackGround()
        TopComponent(context)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginButton {
                if (isBrowserInstalled(context)) {
                    webViewVisible.value = true
                }
                else {
                    dialogState.value = true
                }
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
        AnimatedVisibility(
            visible = webViewVisible.value,
            modifier = Modifier.fillMaxSize(),
            enter = slideInVertically {
                -it
            },
            exit = slideOutVertically {
                -it
            }
        ) {
            GAuthSigninWebView(
                clientId = BuildConfig.CLIENT_ID,
                redirectUri = BuildConfig.REDIRECT_URI,
                onBackPressed = { webViewVisible.value = false },
                callBack = loginCallBack
            )
        }
    }
}