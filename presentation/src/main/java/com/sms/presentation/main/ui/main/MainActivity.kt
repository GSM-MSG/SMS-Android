package com.sms.presentation.main.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.filter.screen.FilterScreen
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    private var doubleBackToExitPressedOnce = false
    private var backPressedTimestamp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        studentListViewModel.getStudentListRequest(1, 20)
        authViewModel.getRoleInfo()
        fillOutViewModel.getMajorList()
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
                                    role = response.data!!,
                                ) {
                                    controlTheStackWhenBackPressed()
                                }
                            }
                            composable("Filter") {
                                val data = remember {
                                    mutableStateOf(
                                        navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                                            "detailStack"
                                        )
                                    )
                                }
                                FilterScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!,
                                    detailStack = (if (data.value != null) data.value!!.split(",") else listOf())
                                )
                            }
                            composable("Search") {
                                setSoftInputMode("RESIZE")
                                val data = remember {
                                    mutableStateOf(
                                        navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                                            "detailStack"
                                        )
                                    )
                                }
                                DetailStackSearchScreen(
                                    navController = navController,
                                    viewModel = searchDetailStackViewModel,
                                    selectedStack = (if (data.value != null) data.value!!.split(",") else listOf(""))
                                ) {
                                    navController.navigate("Filter")
                                }
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
        lifecycleScope.launch {
            fillOutViewModel.getMajorListResponse.collect { response ->
                studentListViewModel.majorList =
                    if (response is Event.Success) {
                        response.data!!.major
                    } else {
                        mutableStateListOf()
                    }
            }
        }
    }

    private fun controlTheStackWhenBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (doubleBackToExitPressedOnce && currentTime - backPressedTimestamp <= 2000) {
            finishAffinity()
        } else {
            doubleBackToExitPressedOnce = true
            backPressedTimestamp = currentTime
            Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSoftInputMode(isType: String = "NOTHING") {
        window.setSoftInputMode(
            when (isType) {
                "PAN" -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                "RESIZE" -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                else -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
            }
        )
    }
}