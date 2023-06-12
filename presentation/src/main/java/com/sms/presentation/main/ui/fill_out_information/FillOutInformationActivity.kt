package com.sms.presentation.main.ui.fill_out_information

import android.os.Bundle
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
                    SchoolLifeScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("WorkCondition") {
                    WorkConditionScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("MilitaryService") {
                    MilitaryServiceScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("Certification") {
                    CertificationScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("ForeignLanguage") {
                    ForeignLanguageScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                        lifecycleScope = lifecycleScope
                    )
                }
                composable("Search") {
                    DetailStackSearchScreen(
                        onClickButton = { detailStackList ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "detailStack",
                                value = detailStackList.joinToString(",")
                            )
                            navController.navigate("Profile")
                        },
                        viewModel = searchDetailStackViewModel
                    )
                }
            }
        }
    }
}