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
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.login.component.LoginScreen
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMajorList()
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
        observeAutoLoginCheck()
    }

    private fun observeLoginEvent() {
        viewModel.gAuthLoginRequest.observe(this) { event ->
            when (event) {
                is Event.Success -> {
                    viewModel.saveTheLoginData(event.data!!)
                    pageController(event.data.isExist)
                }
                else -> {
                    Log.d("login", event.toString())
                }
            }
        }
    }

    private fun observeAutoLoginCheck() {
        viewModel.getMajorList.observe(this) {
            if (it is Event.Success) {
                pageController(true)
            }
        }
    }

    private fun pageController(isExist: Boolean) {
        startActivity(
            Intent(
                this,
                if (isExist) MainActivity::class.java else FillOutInformationActivity::class.java
            )
        )
        finish()
    }
}