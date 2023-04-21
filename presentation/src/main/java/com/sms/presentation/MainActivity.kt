package com.sms.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.unit.dp
import com.msg.gauthsignin.GAuthSigninWebView
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types
import com.sms.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GAuthButton(
                style = Types.Style.DEFAULT,
                actionType = Types.ActionType.SIGNIN,
                colors = Types.Colors.OUTLINE,
                horizontalPaddingValue = 70.dp
            ) {
                setContent {
                    GAuthSigninWebView(
                        clientId = "dfeb634a690a4f76ba9148829171338e2817e94461c34b1794bb3e586b4296bf",
                        redirectUri = "https://sms.msg-team.com/login"
                    ) {
                        authViewModel.gAuthLogin(code = it)
                    }
                }
            }
        }
    }
}