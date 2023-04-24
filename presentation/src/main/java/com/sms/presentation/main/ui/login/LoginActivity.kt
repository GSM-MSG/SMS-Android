package com.sms.presentation.main.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import com.msg.gauthsignin.GAuthSigninWebView
import com.sms.presentation.BuildConfig
import com.sms.presentation.main.ui.MainActivity
import com.sms.presentation.main.ui.login.component.LoginButton
import com.sms.presentation.main.ui.login.component.LoginPageBackGround
import com.sms.presentation.main.ui.login.component.TopComponent
import com.sms.presentation.main.viewmodel.LoginViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        setContent {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            val dpWidth = windowMetrics.bounds.width() - insets.left - insets.right
            Log.d("dp", dpWidth.toString())

            Box {
                LoginPageBackGround()
                TopComponent()
                LoginButton(dpWidth = dpWidth) {
                    setContent {
                        GAuthSigninWebView(
                            clientId = BuildConfig.CLIENT_ID,
                            redirectUri = BuildConfig.REDIRECT_URI
                        ) { code ->
                            viewModel.gAuthLogin(code = code)
                        }
                    }
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