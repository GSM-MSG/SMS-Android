package com.sms.presentation.main.ui.fill_out_information

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillOutInformationActivity : ComponentActivity() {

    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    private var doubleBackToExitPressedOnce = false
    private var backPressedTimestamp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillOutViewModel.getMajorList()

        setContent {
            val navController = rememberNavController()
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
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        controlTheStackWhenBackPressed()
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