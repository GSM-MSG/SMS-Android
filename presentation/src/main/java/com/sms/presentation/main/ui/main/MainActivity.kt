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

private enum class SelectedTechStack {
    MyPage, Project, Filter
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    private val searchDetailStack = mutableStateOf(listOf<String>())

    override fun init() {
        observeEvent()
        authViewModel.getRoleInfo()
        fillOutViewModel.getMajorList()
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            searchDetailStackViewModel.searchResultEvent.collect {
                if (it is Event.Success) {
                    searchDetailStack.value = it.data!!.techStack
                }
            }
        }
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
                        val technologyStackList = remember {
                            mutableStateListOf("Android Studio", "Kotlin")
                        }
                        val technologyStackListByProjectPage = remember {
                            mutableStateListOf(
                                listOf("Kotlin", "Jetpack Compose"),
                                listOf("Android Studio", "XML")
                            )
                        }
                        val filterTechStack = remember {
                            mutableStateListOf("Android Studio", "Kotlin")
                        }
                        val selectedTechStack = remember {
                            mutableStateOf(SelectedTechStack.Filter)
                        }
                        val projectIndex = remember {
                            mutableStateOf(0)
                        }
                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable(MainPage.Main.value) {
                                MainScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!,
                                    onFilterClick = { navController.navigate(MainPage.Filter.value) },
                                    onProfileClick = { role ->
                                        if (role == "ROLE_TEACHER" || role == "ROLE_STUDENT") {
                                            navController.navigate(MainPage.MyPage.value)
                                        } else {
                                            this@MainActivity.startActivity(
                                                Intent(
                                                    this@MainActivity,
                                                    LoginActivity::class.java
                                                )
                                            )
                                            this@MainActivity.finish()
                                        }
                                    }
                                ) {
                                    controlTheStackWhenBackPressed()
                                }
                            }
                            composable(MainPage.Filter.name) {
                                FilterScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    role = response.data!!,
                                    onRemoveFilterDetailStack = {

                                    },
                                    onBackPressed = {
                                        navController.navigate(MainPage.Main.value) {
                                            popUpTo(route = MainPage.Main.value) {
                                                inclusive = false
                                            }
                                        }
                                    },
                                    onChangeToMainPage = {
                                        navController.navigate(MainPage.Main.value)
                                    },
                                    onChangeToSearchPage = {
                                        navController.navigate(MainPage.Search.value)
                                    },
                                    onRightButtonClick = {
                                        navController.navigate(MainPage.Main.value) {
                                            popUpTo(route = MainPage.Main.value) {
                                                inclusive = false
                                            }
                                        }
                                    }
                                )
                            }
                            composable(MainPage.Search.name) {
                                setSoftInputMode("RESIZE")
                                DetailStackSearchScreen(
                                    navController = navController,
                                    onSearchStack = {
                                        searchDetailStackViewModel.searchDetailStack(it)
                                    },
                                    selectedStack = when (selectedTechStack.value) {
                                        SelectedTechStack.MyPage -> technologyStackList
                                        SelectedTechStack.Filter -> filterTechStack
                                        SelectedTechStack.Project -> technologyStackListByProjectPage[projectIndex.value]
                                    },
                                    detailStack = searchDetailStack.value,
                                ) { list ->
                                    when (selectedTechStack.value) {
                                        SelectedTechStack.MyPage -> {
                                            technologyStackList.removeAll(technologyStackList.filter {
                                                !list.contains(it)
                                            })
                                            technologyStackList.addAll(list.filter {
                                                !technologyStackList.contains(it)
                                            })
                                        }

                                        SelectedTechStack.Filter -> {
                                            filterTechStack.removeAll(filterTechStack.filter {
                                                !list.contains(it)
                                            })
                                            filterTechStack.addAll(list.filter {
                                                !filterTechStack.contains(it)
                                            })
                                        }

                                        SelectedTechStack.Project -> {
                                            technologyStackListByProjectPage[projectIndex.value] =
                                                list
                                        }
                                    }
                                    navController.popBackStack()
//                                    studentListViewModel.detailStackList.value =
//                                        navController.previousBackStackEntry?.savedStateHandle?.get<String>(
//                                            "detailStack"
//                                        ) ?: ""
                                }
                            }
                            composable(MainPage.MyPage.value) {
                                MyPageScreen(
                                    majorList = listOf("Android", "Flutter", "Android Studio"),
                                    selectedTechList = technologyStackList,
                                    selectedTechListOnProject = technologyStackListByProjectPage,
                                    onWithdrawal = {
                                        studentListViewModel.withdrawal()
                                        authViewModel.deleteToken()
                                    }, onLogout = {
                                        studentListViewModel.logout()
                                        authViewModel.deleteToken()
                                    }, onClickSearchBar = {
                                        selectedTechStack.value = SelectedTechStack.MyPage
                                        navController.navigate(MainPage.Search.value)
                                    }, onClickProjectSearchBar = {
                                        projectIndex.value = it
                                        selectedTechStack.value = SelectedTechStack.Project
                                        navController.navigate(MainPage.Search.value)
                                    }, onRemoveDetailStack = {
                                        technologyStackList.remove(it)
                                    },
                                    onRemoveProjectDetailStack = { index: Int, value: String ->
                                        technologyStackListByProjectPage[index] =
                                            technologyStackListByProjectPage[index].filter { it != value }
                                    }
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