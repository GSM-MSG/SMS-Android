package com.sms.presentation.main.ui.teacher_registration

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.main.MainActivity
import com.sms.presentation.main.ui.teacher_registration.screen.TeacherRegistrationPageScreen
import com.sms.presentation.main.viewmodel.TeacherViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeacherRegistrationActivity: BaseActivity() {
    private val viewModel by viewModels<TeacherViewModel>()
    override fun init() {
        observeEvent()
        setContent {
            TeacherRegistrationPageScreen(viewModel = viewModel)
        }
    }

    private fun observeEvent(){
        observeCommonTeacherSignupCheck()
        observePrincipalSignUpCheck()
        observeVicePrincipalSignUpCheck()
        observeHeadOfDepartmentTeacherSignUpCheck()
        observeHomeroomTeacherSignUpCheck()
    }

    private fun observeCommonTeacherSignupCheck() = lifecycleScope.launch {
        viewModel.commonResponse.collect {
            when (it) {
                is Event.Success -> pageController()
                else -> Log.e("common SignUp", it.toString())
            }
        }
    }
    private fun observePrincipalSignUpCheck() = lifecycleScope.launch {
        viewModel.principalResponse.collect {
            when (it){
                is Event.Success -> pageController()
                else -> Log.e("principal SignUp", it.toString())
            }
        }
    }
    private fun observeVicePrincipalSignUpCheck() = lifecycleScope.launch {
        viewModel.vicePrincipalResponse.collect{
            when (it) {
                is Event.Success -> pageController()
                else -> Log.e("vicePrincipal SignUp", it.toString())
            }
        }
    }
    private fun observeHeadOfDepartmentTeacherSignUpCheck() = lifecycleScope.launch {
        viewModel.headOfDepartmentResponse.collect {
            when (it) {
                is Event.Success -> pageController()
                else -> Log.e("headOfDepartmentTeacher SignUp", it.toString())
            }
        }
    }
    private fun observeHomeroomTeacherSignUpCheck() = lifecycleScope.launch {
        viewModel.homeroomResponse.collect {
            when (it) {
                is Event.Success -> pageController()
                else -> Log.e("homeroomTeacher SignUp", it.toString())
            }
        }
    }

    private fun pageController(){
        startActivity(
            Intent(
                this,MainActivity::class.java
            )
        )
    }
}