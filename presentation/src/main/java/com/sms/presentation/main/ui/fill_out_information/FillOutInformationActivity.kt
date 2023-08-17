package com.sms.presentation.main.ui.fill_out_information

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.component.FillOutInformationTopBarComponent
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillOutInformationActivity : BaseActivity() {

    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun init() {
        fillOutViewModel.getMajorList()

        setContent {
            val navController = rememberNavController()
            val bottomSheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val bottomSheetContent = remember {
                mutableStateOf<@Composable ColumnScope.() -> Unit>({})
            }
            val currentRoute = remember {
                mutableStateOf("Profile")
            }

            ModalBottomSheetLayout(
                sheetContent = bottomSheetContent.value,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetState = bottomSheetState
            ) {
                SMSTheme { colors, _ ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors.WHITE)
                    ) {
                        FillOutInformationTopBarComponent(currentRoute = currentRoute.value) {
                            navController.popBackStack()
                        }
                        NavHost(
                            navController = navController,
                            startDestination = "Award"
                        ) {
                            composable("Profile") {
                                currentRoute.value = "Profile"
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
                                    detailStack = (if (data.value != null) data.value!!.split(",") else listOf()),
                                    bottomSheetState = bottomSheetState
                                ) {
                                    bottomSheetContent.value = it
                                }
                            }
                            composable("SchoolLife") {
                                currentRoute.value = "SchoolLife"
                                setSoftInputMode()
                                SchoolLifeScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                                )
                            }
                            composable("WorkCondition") {
                                currentRoute.value = "WorkCondition"
                                setSoftInputMode("PAN")
                                WorkConditionScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                    bottomSheetState = bottomSheetState
                                ) {
                                    bottomSheetContent.value = it
                                }
                            }
                            composable("MilitaryService") {
                                currentRoute.value = "MilitaryService"
                                setSoftInputMode()
                                MilitaryServiceScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                    bottomSheetState = bottomSheetState
                                ) {
                                    bottomSheetContent.value = it
                                }
                            }
                            composable("Certification") {
                                currentRoute.value = "Certification"
                                setSoftInputMode("PAN")
                                CertificationScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                                )
                            }
                            composable("ForeignLanguage") {
                                currentRoute.value = "ForeignLanguage"
                                setSoftInputMode("PAN")
                                ForeignLanguageScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                    lifecycleScope = lifecycleScope
                                )
                            }
                            composable("Projects") {
                                currentRoute.value = "Projects"
                                setSoftInputMode("PAN")
                                ProjectsScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                    bottomSheetState = bottomSheetState
                                ) {
                                    bottomSheetContent.value = it
                                }
                            }
                            composable("Award") {
                                currentRoute.value = "Award"
                                setSoftInputMode("PAN")
                                AwardScreen()
                            }
                            composable("Search") {
                                currentRoute.value = "Search"
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
}