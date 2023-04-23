package com.sms.presentation.main.ui

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msg.gauthsignin.GAuthSigninWebView
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.BuildConfig
import com.sms.presentation.main.viewmodel.LoginViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        setContent {
            val display: Display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val density = resources.displayMetrics.density
            val dpWidth = outMetrics.widthPixels / density

            SMSTheme { colors, typography ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15f))
                    Text(
                        text = "Student\nManagement\nService",
                        style = typography.headline1,
                        fontWeight = FontWeight(700),
                        color = colors.WHITE,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "학생 정보 통합관리 서비스",
                        style = typography.title2,
                        fontWeight = FontWeight(600),
                        color = colors.N20
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GAuthButton(
                        style = Types.Style.ROUNDED,
                        actionType = Types.ActionType.SIGNIN,
                        colors = Types.Colors.WHITE,
                        horizontalPaddingValue = (dpWidth / 2 - 110).dp
                    ) {
                        setContent {
                            GAuthSigninWebView(
                                clientId = BuildConfig.CLIENT_ID,
                                redirectUri = BuildConfig.REDIRECT_URI
                            ) {
                                viewModel.gAuthLogin(code = it)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                }
            }
        }
    }

    private fun observeEvent() {
        observeLoginEvent()
    }

    private fun observeLoginEvent() {
        viewModel.gAuthLoginRequest.observe(this) { event ->
            when (event) {
                Event.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else -> {
                    Log.d("login", event.toString())
                }
            }
        }
    }
}