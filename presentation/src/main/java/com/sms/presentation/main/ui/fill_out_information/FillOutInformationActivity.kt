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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            val detailStackList = remember {
                mutableStateMapOf<String, List<String>>()
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
                            startDestination = "Projects"
                        ) {
                            composable("Profile") {
                                currentRoute.value = "Profile"
                                setSoftInputMode("PAN")
                                ProfileScreen(
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                    detailStack = detailStackList["Profile"] ?: emptyList(),
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
                                    bottomSheetState = bottomSheetState,
                                    detailStackList = detailStackList
                                ) {
                                    bottomSheetContent.value = it
                                }
                            }
                            composable(
                                "Search/{idx}",
                                arguments = listOf(
                                    navArgument("idx") { type = NavType.StringType }
                                )
                            ) { backStackEntry ->
                                currentRoute.value = "Search"
                                setSoftInputMode("RESIZE")
                                val idx = remember {
                                    mutableStateOf(backStackEntry.arguments?.getString("idx") ?: "")
                                }

                                DetailStackSearchScreen(
                                    navController = navController,
                                    viewModel = searchDetailStackViewModel,
                                    selectedStack = detailStackList[idx.value] ?: listOf(""),
                                ) {
                                    detailStackList[idx.value] = it
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}