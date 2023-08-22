package com.sms.presentation.main.ui.main

import android.content.Intent
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
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.filter.screen.FilterScreen
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.ui.mypage.MyPageScreen
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private enum class MainPage(val value: String) {
    Main("Main"),
    Filter("Filter"),
    Search("Search"),
    MyPage("MyPage")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    override fun init() {
        observeEvent()
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
                            composable(MainPage.Main.value) {
                                MainScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!,
                                ) {
                                    controlTheStackWhenBackPressed()
                                }
                            }
                            composable(MainPage.Filter.name) {
                                FilterScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!
                                )
                            }
                            composable(MainPage.Search.name) {
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
                                    selectedStack = (if (data.value != null) data.value!!.split(",") else listOf(
                                        ""
                                    ))
                                ) {
                                    navController.navigate(MainPage.Filter.name)
                                    studentListViewModel.detailStackList.value =
                                        navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                                            "detailStack"
                                        ) ?: ""
                                }
                            }
                            composable(MainPage.MyPage.value) {
                                MyPageScreen(
                                    onWithdrawal = {
                                        studentListViewModel.withdrawal()
                                        authViewModel.deleteToken()
                                    }, onLogout = {
                                        studentListViewModel.logout()
                                        authViewModel.deleteToken()
                                    })
                            }
                        }
                    }
                } else {
                    setContent {
                        SmsDialog(
                            title = "에러",
                            msg = "알 수 없는 오류 발생",
                            outLineButtonText = "취소",
                            importantButtonText = "확인",
                            outlineButtonOnClick = { finish() },
                            importantButtonOnClick = { finish() }
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
}