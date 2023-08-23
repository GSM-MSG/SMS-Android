package com.sms.presentation.main.ui.fill_out_information

import android.net.Uri
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.msg.sms.design.component.snackbar.SmsSnackBar
import com.msg.sms.design.icon.ExclamationMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.component.FillOutInformationTopBarComponent
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.MajorSelectorBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.MilitarySelectorBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.PhotoPickBottomSheet
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private enum class BottomSheetValues {
    PhotoPicker,
    Major,
    WorkingForm,
    Military,
    Date
}

enum class FillOutPage(val value: String) {
    Profile("Profile"),
    SchoolLife("SchoolLife"),
    WorkCondition("WorkCondition"),
    MilitaryService("MilitaryService"),
    Certification("Certification"),
    ForeignLanguage("ForeignLanguage"),
    Projects("Projects"),
    Search("Search")
}

@AndroidEntryPoint
class FillOutInformationActivity : BaseActivity() {

    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()
    private val searchDetailStack = mutableStateOf(listOf<String>())

    @OptIn(ExperimentalMaterialApi::class)
    override fun init() {
        fillOutViewModel.getMajorList()
        lifecycleScope.launch {
            searchDetailStack()
        }

        setContent {
            val scope = rememberCoroutineScope()
            val bottomSheetValues = remember {
                mutableStateOf(BottomSheetValues.Major)
            }
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
            val snackBarVisible = remember {
                mutableStateOf(false)
            }

            //PhotoPickBottomSheet
            val profileImageUri = remember {
                mutableStateOf(Uri.EMPTY)
            }
            val isImageExtensionInCorrect = remember {
                mutableStateOf(false)
            }

            //MajorSelectorBottomSheet
            val selectedMajor = remember {
                mutableStateOf("")
            }

            //WorkingFormBottomSheet
            val selectedWorkingCondition = remember {
                mutableStateOf("")
            }

            //MilitaryServiceBottomSheet
            val selectedMilitaryService = remember {
                mutableStateOf("")
            }

            ModalBottomSheetLayout(
                sheetContent = {
                    when (bottomSheetValues.value) {
                        BottomSheetValues.PhotoPicker -> {
                            PhotoPickBottomSheet(
                                bottomSheetState = bottomSheetState,
                                onProfileImageUriChanged = { uri, extension ->
                                    isImageExtensionInCorrect.value = extension
                                    profileImageUri.value = if (extension) Uri.EMPTY else uri
                                }
                            )
                        }
                        BottomSheetValues.Major -> {
                            val list = fillOutViewModel.getMajorListResponse.collectAsState()

                            MajorSelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                majorList = if (list.value.data != null) list.value.data!!.major else listOf(
                                    ""
                                ),
                                selectedMajor = selectedMajor.value,
                                onSelectedMajhorChange = {
                                    selectedMajor.value = it
                                },
                            )
                        }
                        BottomSheetValues.WorkingForm -> {
                            val data = fillOutViewModel.getEnteredWorkConditionInformation()

                            MajorSelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                majorList = listOf("정규직", "비정규직", "계약직", "인턴"),
                                selectedMajor = if (selectedWorkingCondition.value == "") data.formOfEmployment else selectedWorkingCondition.value,
                                onSelectedMajhorChange = { selectedWorkingCondition.value = it }
                            )
                        }
                        BottomSheetValues.Military -> {
                            val data = fillOutViewModel.getEnteredMilitaryServiceInformation()

                            MilitarySelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                militaryServiceList = listOf("병특 희망", "희망하지 않음", "상관없음", "해당 사항 없음"),
                                selectedMilitaryService = if (selectedMilitaryService.value == "") data.militaryService else selectedMilitaryService.value,
                                onSelectedMilitaryServiceChange = {
                                    selectedMilitaryService.value = it
                                },
                            )
                        }
                        BottomSheetValues.Date -> {

                        }
                    }
                },
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetState = bottomSheetState
            ) {
                SMSTheme { colors, _ ->
                    Box {
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
                                startDestination = FillOutPage.Profile.value
                            ) {
                                composable(FillOutPage.Profile.value) {
                                    currentRoute.value = FillOutPage.Profile.value
                                    setSoftInputMode("PAN")
                                    ProfileScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        detailStack = detailStackList[FillOutPage.Profile.value]
                                            ?: emptyList(),
                                        profileImageUri = profileImageUri.value,
                                        selectedMajor = selectedMajor.value,
                                        isImageExtensionInCorrect = isImageExtensionInCorrect.value,
                                        onPhotoPickBottomSheetOpenButtonClick = {
                                            scope.launch { bottomSheetState.show() }
                                            bottomSheetValues.value = BottomSheetValues.PhotoPicker
                                        },
                                        onMajorBottomSheetOpenButtonClick = {
                                            scope.launch { bottomSheetState.show() }
                                            bottomSheetValues.value = BottomSheetValues.Major
                                        },
                                        onDialogDissmissButtonClick = {
                                            isImageExtensionInCorrect.value = false
                                        }
                                    )
                                }
                                composable(FillOutPage.SchoolLife.value) {
                                    currentRoute.value = FillOutPage.SchoolLife.value
                                    setSoftInputMode()
                                    SchoolLifeScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                                    )
                                }
                                composable(FillOutPage.WorkCondition.value) {
                                    currentRoute.value = FillOutPage.WorkCondition.value
                                    setSoftInputMode("PAN")
                                    WorkConditionScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        selectedWorkingCondition = selectedWorkingCondition.value,
                                        onWorkingConditionBottomSheetOpenButtonClick = {
                                            scope.launch { bottomSheetState.show() }
                                            bottomSheetValues.value = BottomSheetValues.WorkingForm
                                        }
                                    )
                                }
                                composable(FillOutPage.MilitaryService.value) {
                                    currentRoute.value = FillOutPage.MilitaryService.value
                                    setSoftInputMode()
                                    MilitaryServiceScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        selectedMilitaryService = selectedMilitaryService.value,
                                        onMilitaryServiceBottomSheetOpenButtonClick = {
                                            scope.launch { bottomSheetState.show() }
                                            bottomSheetValues.value = BottomSheetValues.Military
                                        }
                                    )
                                }
                                composable(FillOutPage.Certification.value) {
                                    currentRoute.value = FillOutPage.Certification.value
                                    setSoftInputMode("PAN")
                                    CertificationScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                                    )
                                }
                                composable(FillOutPage.ForeignLanguage.value) {
                                    currentRoute.value = FillOutPage.ForeignLanguage.value
                                    setSoftInputMode("PAN")
                                    ForeignLanguageScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        lifecycleScope = lifecycleScope
                                    )
                                }
                                composable(FillOutPage.Projects.value) {
                                    currentRoute.value = FillOutPage.Projects.value
                                    setSoftInputMode("PAN")
                                    ProjectsScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        bottomSheetState = bottomSheetState,
                                        detailStackList = detailStackList,
                                        onSnackBarVisibleChanged = { snackBarVisible.value = true }
                                    ) {
                                        bottomSheetContent.value = it
                                    }
                                }
                                composable(
                                    "${FillOutPage.Search.value}/{idx}",
                                    arguments = listOf(
                                        navArgument("idx") { type = NavType.StringType }
                                    )
                                ) { backStackEntry ->
                                    currentRoute.value = FillOutPage.Search.value
                                    setSoftInputMode("RESIZE")
                                    val idx = remember {
                                        mutableStateOf(
                                            backStackEntry.arguments?.getString("idx") ?: ""
                                        )
                                    }

                                    DetailStackSearchScreen(
                                        navController = navController,
                                        detailStack = searchDetailStack.value,
                                        selectedStack = detailStackList[idx.value] ?: listOf(""),
                                        onSearchStack = {
                                            searchDetailStackViewModel.searchDetailStack(it)
                                        }
                                    ) {
                                        detailStackList[idx.value] = it
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                        SmsSnackBar(
                            text = "이미지는 최대 4장까지만 추가 할 수 있어요.",
                            visible = snackBarVisible.value,
                            leftIcon = { ExclamationMarkIcon() },
                            onClick = { snackBarVisible.value = false },
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }

    private suspend fun searchDetailStack() {
        searchDetailStackViewModel.searchResultEvent.collect {
            when (it) {
                Event.Success<List<String>>() -> {
                    searchDetailStack.value = it.data!!.techStack
                }

                else -> {}
            }
        }
    }
}