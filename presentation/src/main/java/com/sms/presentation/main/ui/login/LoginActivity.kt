package com.sms.presentation.main.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.msg.gauthsignin.GAuthSigninWebView
import com.sms.presentation.BuildConfig
import com.sms.presentation.main.ui.MainActivity
import com.sms.presentation.main.ui.login.component.LoginScreen
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
            LoginScreen(context = this@LoginActivity) {
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