package com.sms.presentation.main.ui.fill_out_information

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.component.FilloutStatusProgressBar
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillOutInformationActivity : BaseActivity() {

    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    override fun init() {
        fillOutViewModel.getMajorList()

        setContent {
            val navController = rememberNavController()
            val currentNavBackStackSize = remember {
                mutableStateOf(navController.backQueue.size)
            }
            SMSTheme { colors, _ ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.WHITE)
                ) {
                    TopBarComponent(
                        text = "정보 입력",
                        leftIcon = { BackButtonIcon() },
                        rightIcon = null
                    ) {
                        navController.popBackStack()
                    }
                    FilloutStatusProgressBar(currentNavBackStackSize.value)
                    SmsSpacer()
                    NavHost(
                        navController = navController,
                        startDestination = "Profile"
                    ) {
                        composable("Profile") {
                            setSoftInputMode("PAN")
                            val data = remember {
                                mutableStateOf(
                                    navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                                        "detailStack"
                                    )
                                )
                            }
                            ProfileScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                detailStack = (if (data.value != null) data.value!!.split(",") else listOf())
                            )
                        }
                        composable("SchoolLife") {
                            setSoftInputMode()
                            SchoolLifeScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                            )
                        }
                        composable("WorkCondition") {
                            setSoftInputMode("PAN")
                            WorkConditionScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                            )
                        }
                        composable("MilitaryService") {
                            setSoftInputMode()
                            MilitaryServiceScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                            )
                        }
                        composable("Certification") {
                            setSoftInputMode("PAN")
                            CertificationScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                            )
                        }
                        composable("ForeignLanguage") {
                            setSoftInputMode("PAN")
                            ForeignLanguageScreen(
                                navController = navController,
                                viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                lifecycleScope = lifecycleScope
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
                                selectedStack = (if (data.value != null) data.value!!.split(",") else listOf())
                            ) {
                                navController.navigate("Profile")
                            }
                        }
                    }
                }
            }
        }
    }
}