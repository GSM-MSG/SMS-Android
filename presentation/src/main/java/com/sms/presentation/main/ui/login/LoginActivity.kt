package com.sms.presentation.main.ui.login

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.login.component.LoginScreen
import com.sms.presentation.main.ui.main.MainActivity
import com.sms.presentation.main.ui.teacher_registration.TeacherRegistrationActivity
import com.sms.presentation.main.ui.util.setTransparentStatusBar
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var isExist: String
    private lateinit var role: String

    override fun init() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.accessValidationResponse.value is Event.Loading
            }
        }
        viewModel.accessValidation()
        observeEvent()
        this.setTransparentStatusBar()
        setContent {
            LoginScreen(
                context = this@LoginActivity,
                loginCallBack = { code ->
                    viewModel.gAuthLogin(code)
                },
                onLookAroundToGuestButtonClick = {
                    pageController(true)
                }
            )
        }
    }

    private fun observeEvent() {
        observeLoginEvent()
        observeAutoLoginCheck()
        observeSaveTokenEvent()
    }

    private fun observeAutoLoginCheck() = lifecycleScope.launch {
        viewModel.accessValidationResponse.collect {
            when (it) {
                is Event.Success -> pageController(it.data!!.isExist)
                else -> {
                    Log.d("loginCheck", it.toString())
                }
            }
        }
    }

    private fun observeLoginEvent() {
        viewModel.gAuthLoginRequest.observe(this) { event ->
            when (event) {
                is Event.Success -> {
                    viewModel.saveTheLoginData(event.data!!)
                    isExist = event.data.isExist.toString()
                    role = event.data.role
                }

                else -> {
                    Log.d("login", event.toString())
                }
            }
        }
    }

    private fun observeSaveTokenEvent() {
        viewModel.saveTokenRequest.observe(this) { event ->
            when (event) {
                is Event.Success -> {
                    registrationPageController(isExist.toBoolean(), role)
                }

                else -> {
                    Log.d("login", event.toString())
                }
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

    private fun registrationPageController(isExist: Boolean, role: String){
        startActivity(
            Intent(
                this,
                if (isExist) MainActivity::class.java
                else {
                    if (role == "ROLE_STUDENT") FillOutInformationActivity::class.java
                    else TeacherRegistrationActivity::class.java
                }
            )
        )

    }
}