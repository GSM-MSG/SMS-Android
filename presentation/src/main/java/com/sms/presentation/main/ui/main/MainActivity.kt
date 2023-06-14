package com.sms.presentation.main.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.main.ui.filter.screen.FilterScreen
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        studentListViewModel.getStudentListRequest(1, 20)
        authViewModel.getRoleInfo()
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            studentListViewModel.logoutResponse.collect {
                if (it is Event.Success) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            studentListViewModel.withdrawalResponse.collect {
                if (it is Event.Success) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            authViewModel.getRoleResponse.collect { response ->
                if (response is Event.Success) {
                    setContent {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable("Main") {
                                MainScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!
                                )
                            }
                            composable("Filter") {
                                FilterScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!
                                )
                            }
                        }
                    }
                } else {
                    setContent {
                        SmsDialog(
                            title = "에러",
                            msg = "알 수 없는 오류 발생",
                            outLineButtonText = "취소",
                            normalButtonText = "확인",
                            outlineButtonOnClick = { finish() },
                            normalButtonOnClick = { finish() }
                        )
                    }
                }
            }
        }
    }
}