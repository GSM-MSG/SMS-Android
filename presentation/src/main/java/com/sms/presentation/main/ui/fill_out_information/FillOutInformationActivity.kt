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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.lottie.SmsLoadingLottie
import com.msg.sms.design.component.snackbar.SmsSnackBar
import com.msg.sms.design.icon.ExclamationMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.request.*
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.component.FillOutInformationTopBarComponent
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.DatePickerBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.MajorSelectorBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.MilitarySelectorBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.PhotoPickBottomSheet
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private enum class BottomSheetValues {
    PhotoPicker,
    Major,
    WorkingForm,
    Military,
    Date
}

private enum class DetailSearchLocation {
    Profile,
    Projects
}

enum class FillOutPage(val value: String) {
    Profile("Profile"),
    SchoolLife("SchoolLife"),
    WorkCondition("WorkCondition"),
    MilitaryService("MilitaryService"),
    Certification("Certification"),
    ForeignLanguage("ForeignLanguage"),
    Projects("Projects"),
    Award("Award"),
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
            val focusManager = LocalFocusManager.current
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val bottomSheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val bottomSheetValues = remember {
                mutableStateOf(BottomSheetValues.Major)
            }
            val detailStackSearchLocation = remember {
                mutableStateOf(DetailSearchLocation.Profile)
            }
            val currentRoute = remember {
                mutableStateOf(FillOutPage.Profile.value)
            }
            val snackBarVisible = remember {
                mutableStateOf(false)
            }
            val snackBarText = remember {
                mutableStateOf("")
            }
            val projectIndex = remember {
                mutableStateOf(0)
            }

            //enteredData
            val enteredProfileData = fillOutViewModel.getEnteredProfileInformation()
            val enteredSchoolLifeData = fillOutViewModel.getEnteredSchoolLifeInformation()
            val enteredWorkConditionData = fillOutViewModel.getEnteredWorkConditionInformation()
            val enteredMilitaryData = fillOutViewModel.getEnteredMilitaryServiceInformation()
            val enteredCertificateData = fillOutViewModel.getEnteredCertification().certifications
            val enteredForeignLanguagesData = fillOutViewModel.getEnteredForeignLanguagesInformation().foreignLanguages
            val enteredProjectsData = fillOutViewModel.getEnteredProjectsInformation().projects

            //data
            val profileData = remember {
                mutableStateOf(enteredProfileData)
            }
            val projectList = remember {
                mutableStateListOf(*enteredProjectsData.toTypedArray())
            }
            val awardData = remember {
                mutableStateListOf(AwardData(isToggleOpen = true))
            }
            val majorList = fillOutViewModel.getMajorListResponse.collectAsState()

            //DetailStacks
            val profileDetailTechStack = remember {
                mutableStateListOf(
                    *(enteredProfileData.techStack.filter { it != "" }).toTypedArray()
                )
            }
            val projectsDetailTechStack = remember {
                mutableStateListOf(*enteredProjectsData.map { it.technologyOfUse }.toTypedArray())
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

            //DateBottomSheet
            val isProjectDate = remember {
                mutableStateOf(true)
            }
            val isProjectStartDate = remember {
                mutableStateOf(true)
            }
            val awardIndex = remember {
                mutableStateOf(0)
            }
            val awardDateMap = remember {
                mutableStateMapOf<Int, String>()
            }

            //Dialog
            val dialogVisible = remember {
                mutableStateOf(false)
            }
            val dialogTitle = remember {
                mutableStateOf("")
            }
            val dialogText = remember {
                mutableStateOf("")
            }
            val loadingModalState = remember {
                mutableStateOf(false)
            }

            if (loadingModalState.value) {
                Dialog(onDismissRequest = { }) {
                    SmsLoadingLottie(modifier = Modifier.size(80.dp))
                }
            }

            if (dialogVisible.value) {
                SmsDialog(
                    title = dialogTitle.value,
                    msg = dialogText.value,
                    outLineButtonText = "취소",
                    importantButtonText = "확인",
                    outlineButtonOnClick = { dialogVisible.value = false },
                    importantButtonOnClick = { dialogVisible.value = false }
                )
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
                            MajorSelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                majorList = if (majorList.value.data != null) majorList.value.data!!.major else listOf(),
                                selectedMajor = selectedMajor.value,
                                onSelectedMajhorChange = {
                                    selectedMajor.value = it
                                },
                            )
                        }
                        BottomSheetValues.WorkingForm -> {
                            val workConditionData =
                                fillOutViewModel.getEnteredWorkConditionInformation()

                            MajorSelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                majorList = listOf("정규직", "비정규직", "계약직", "인턴"),
                                selectedMajor = if (selectedWorkingCondition.value == "") workConditionData.formOfEmployment else selectedWorkingCondition.value,
                                onSelectedMajhorChange = { selectedWorkingCondition.value = it }
                            )
                        }
                        BottomSheetValues.Military -> {
                            val militaryServiceData =
                                fillOutViewModel.getEnteredMilitaryServiceInformation()

                            MilitarySelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                militaryServiceList = listOf(
                                    "병특 희망",
                                    "희망하지 않음",
                                    "상관없음",
                                    "해당 사항 없음"
                                ),
                                selectedMilitaryService = if (selectedMilitaryService.value == "") militaryServiceData.militaryService else selectedMilitaryService.value,
                                onSelectedMilitaryServiceChange = {
                                    selectedMilitaryService.value = it
                                },
                            )
                        }
                        BottomSheetValues.Date -> {
                            DatePickerBottomSheet(
                                bottomSheetState = bottomSheetState,
                                onDateValueChanged = { date ->
                                    val startDate = projectList[projectIndex.value].startDate
                                    val endDate = projectList[projectIndex.value].endDate

                                    when {
                                        isProjectDate.value && isProjectStartDate.value -> {
                                            if (endDate.isEmpty()) {
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(startDate = date)
                                            } else {
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(
                                                        startDate = minOf(
                                                            endDate,
                                                            date
                                                        )
                                                    )
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(
                                                        endDate = maxOf(
                                                            endDate,
                                                            date
                                                        )
                                                    )
                                            }
                                        }
                                        isProjectDate.value && !isProjectStartDate.value -> {
                                            if (startDate.isEmpty()) {
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(endDate = date)
                                            } else {
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(
                                                        startDate = minOf(
                                                            startDate,
                                                            date
                                                        )
                                                    )
                                                projectList[projectIndex.value] =
                                                    projectList[projectIndex.value].copy(
                                                        endDate = maxOf(
                                                            startDate,
                                                            date
                                                        )
                                                    )
                                            }
                                        }
                                        !isProjectDate.value -> {
                                            awardDateMap[awardIndex.value] = date
                                        }
                                    }
                                }
                            )
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
                                        data = profileData.value,
                                        detailStacks = profileDetailTechStack,
                                        profileImageUri = profileImageUri.value,
                                        selectedMajor = selectedMajor.value,
                                        isImageExtensionInCorrect = isImageExtensionInCorrect.value,
                                        onPhotoPickBottomSheetOpenButtonClick = {
                                            bottomSheetValues.value = BottomSheetValues.PhotoPicker
                                            scope.launch { bottomSheetState.show() }
                                        },
                                        onMajorBottomSheetOpenButtonClick = {
                                            if (majorList.value.data != null) {
                                                bottomSheetValues.value = BottomSheetValues.Major
                                                scope.launch { bottomSheetState.show() }
                                            }
                                        },
                                        onDialogDissmissButtonClick = {
                                            isImageExtensionInCorrect.value = false
                                        },
                                        onSnackBarVisibleChanged = { text ->
                                            scope.launch {
                                                snackBarVisible.value = true
                                                snackBarText.value = text
                                                delay(1.5.seconds)
                                                focusManager.clearFocus()
                                                snackBarVisible.value = false
                                            }
                                        },
                                        onProjectValueChanged = {
                                            profileData.value = it
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
                                            bottomSheetValues.value = BottomSheetValues.WorkingForm
                                            scope.launch { bottomSheetState.show() }
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
                                            bottomSheetValues.value = BottomSheetValues.Military
                                            scope.launch { bottomSheetState.show() }
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
                                        projects = projectList,
                                        detailStacks = projectsDetailTechStack,
                                        onAddButtonClick = {
                                            projectList.add(ProjectInfo())
                                            projectsDetailTechStack.add(emptyList())
                                        },
                                        onNextButtonClick = {
                                            fillOutViewModel.setEnteredProjectsInformation(
                                                projectList.filter { project ->
                                                    project.name.isNotEmpty() ||
                                                    project.icon != Uri.EMPTY ||
                                                    project.preview.isNotEmpty() ||
                                                    project.technologyOfUse.isNotEmpty() ||
                                                    project.description.isNotEmpty() ||
                                                    project.keyTask.isNotEmpty() ||
                                                    project.endDate.isNotEmpty() ||
                                                    project.startDate.isNotEmpty() ||
                                                    project.relatedLinkList.first() != Pair("", "")
                                                }
                                            )
                                            //TODO : Kimhyunseung - 이름, 아이콘, 설명, 작업, 기간 (필수 입력 요소들) 입력되어있는지 검사 로직 추가
                                            navController.navigate("Award")
                                        },
                                        onCancelButtonClick = { index ->
                                            projectList.removeAt(index)
                                            projectsDetailTechStack.removeAt(index)
                                        },
                                        onDateBottomSheetOpenButtonClick = { index, isStartDate ->
                                            bottomSheetValues.value = BottomSheetValues.Date
                                            isProjectStartDate.value = isStartDate
                                            isProjectDate.value = true
                                            projectIndex.value = index
                                            scope.launch { bottomSheetState.show() }
                                        },
                                        onDetailStackSearchBarClick = { index ->
                                            projectIndex.value = index
                                            detailStackSearchLocation.value =
                                                DetailSearchLocation.Projects
                                            navController.navigate("Search")
                                        },
                                        onProjectItemToggleIsOpenValueChanged = { index, visible ->
                                            projectList[index] = projectList[index].copy(isToggleOpen = visible)
                                        },
                                        onSnackBarVisibleChanged = { text ->
                                            scope.launch {
                                                snackBarVisible.value = true
                                                snackBarText.value = text
                                                delay(1.5.seconds)
                                                focusManager.clearFocus()
                                                snackBarVisible.value = false
                                            }
                                        },
                                        onProjectNameValueChanged = { index, name ->
                                            projectList[index] = projectList[index].copy(name = name)
                                        },
                                        onProjectIconValueChanged = { index, icon ->
                                            projectList[index] = projectList[index].copy(icon = icon)
                                        },
                                        onProjectPreviewsValueChanged = { index, previews ->
                                            projectList[index] = projectList[index].copy(preview = previews)
                                        },
                                        onProjectTechStackValueChanged = { index, list ->
                                            projectsDetailTechStack[index] = list
                                        },
                                        onProjectDescriptionValueChanged = { index, description ->
                                            projectList[index] = projectList[index].copy(description = description)
                                        },
                                        onProjectKeyTaskValueChanged = { index, keytask ->
                                            projectList[index] = projectList[index].copy(keyTask = keytask)
                                        },
                                        onProjectRelatedLinksValueChanged = { index, links ->
                                            projectList[index] = projectList[index].copy(relatedLinkList = links)
                                        }
                                    )
                                }
                                composable(FillOutPage.Award.value) {
                                    currentRoute.value = FillOutPage.Award.value
                                    setSoftInputMode("PAN")
                                    AwardScreen(
                                        data = awardData,
                                        awardDateMap = awardDateMap,
                                        onDateBottomSheetOpenButtonClick = { index ->
                                            awardIndex.value = index
                                            isProjectDate.value = false
                                            bottomSheetValues.value = BottomSheetValues.Date
                                            scope.launch { bottomSheetState.show() }
                                        },
                                        onPreviousButtonClick = {
                                            awardDateMap.clear()
                                            navController.popBackStack()
                                        },
                                        onAddButtonClick = {
                                            awardData.add(AwardData())
                                        },
                                        onCancelButtonClick = { index ->
                                            awardData.removeAt(index)
                                        },
                                        onCompleteButtonClick = {
                                            loadingModalState.value = true
                                            fillOutViewModel.setEnteredAwardsInformation(
                                                awardData.filter { award ->
                                                    award.name.isNotEmpty() ||
                                                    award.type.isNotEmpty() ||
                                                    award.date.isNotEmpty()
                                                }
                                            )

                                            //이미지 업로드 요청
                                            try {
                                                fillOutViewModel.profileImageUpload(this@FillOutInformationActivity)
                                                fillOutViewModel.projectsIconUpload(this@FillOutInformationActivity)
                                                fillOutViewModel.projectsPreview(this@FillOutInformationActivity)
                                            } catch (e: RuntimeException) {
                                                dialogVisible.value = true
                                                dialogTitle.value = "실패"
                                                dialogText.value = e.message ?: "알 수 없는 에러"
                                            }

                                            //학생 정보 기입 요청
                                            lifecycleScope.launch {
                                                fillOutViewModel.imageUploadComplete.collect { complete ->
                                                    if (complete) {
                                                        fillOutViewModel.enterStudentInformation(
                                                            major = enteredProfileData.major,
                                                            techStack = enteredProfileData.techStack,
                                                            profileImgUrl = fillOutViewModel.profileImageUploadResponse.value.data!!,
                                                            introduce = enteredProfileData.introduce,
                                                            portfolioUrl = enteredProfileData.portfolioUrl,
                                                            contactEmail = enteredProfileData.contactEmail,
                                                            formOfEmployment = enteredWorkConditionData.formOfEmployment.toEnum(),
                                                            salary = enteredWorkConditionData.salary.toInt(),
                                                            region = enteredWorkConditionData.regions,
                                                            gsmAuthenticationScore = enteredSchoolLifeData.gsmAuthenticationScore.toInt(),
                                                            certificate = enteredCertificateData,
                                                            militaryService = enteredMilitaryData.militaryService.toEnum(),
                                                            languageCertificate = enteredForeignLanguagesData.map {
                                                                CertificateInformationModel(
                                                                    languageCertificateName = it.languageCertificateName,
                                                                    score = it.score
                                                                )
                                                            },
                                                            projects = enteredProjectsData.mapIndexed { index, item ->
                                                                ProjectModel(
                                                                    name = item.name,
                                                                    icon = fillOutViewModel.projectsIconImageUploadResponse.value.data!![index],
                                                                    previewImages = fillOutViewModel.projectsPreviewImagesUploadResponse.value.data!![index],
                                                                    description = item.description,
                                                                    links = item.relatedLinkList.map {
                                                                        ProjectRelatedLinkModel(
                                                                            name = it.first,
                                                                            url = it.second
                                                                        )
                                                                    },
                                                                    techStacks = item.technologyOfUse,
                                                                    myActivity = item.keyTask,
                                                                    inProgress = ProjectDateModel(
                                                                        item.startDate,
                                                                        item.endDate
                                                                    )
                                                                )
                                                            },
                                                            award = awardData.map {
                                                                PrizeModel(
                                                                    name = it.name,
                                                                    date = it.date,
                                                                    type = it.type
                                                                )
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        },
                                        onAwardValueChanged = { index, award ->
                                            awardData[index] = award
                                        }
                                    )
                                }
                                composable(FillOutPage.Search.value) {
                                    currentRoute.value = FillOutPage.Search.value
                                    setSoftInputMode("RESIZE")
                                    DetailStackSearchScreen(
                                        navController = navController,
                                        detailStack = searchDetailStack.value,
                                        selectedStack = when (detailStackSearchLocation.value) {
                                            DetailSearchLocation.Profile -> profileDetailTechStack
                                            DetailSearchLocation.Projects -> projectsDetailTechStack[projectIndex.value]
                                        },
                                        onSearchStack = {
                                            searchDetailStackViewModel.searchDetailStack(it)
                                        }
                                    ) { stack ->
                                        when (detailStackSearchLocation.value) {
                                            DetailSearchLocation.Profile -> {
                                                profileDetailTechStack.removeAll(
                                                    profileDetailTechStack.filter {
                                                        !stack.contains(it)
                                                    })
                                                profileDetailTechStack.addAll(stack.filter {
                                                    !profileDetailTechStack.contains(it)
                                                })
                                            }
                                            DetailSearchLocation.Projects -> {
                                                projectsDetailTechStack[projectIndex.value] = stack
                                            }
                                        }
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                        SmsSnackBar(
                            text = snackBarText.value,
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

    private fun String.toEnum(): String {
        return when (this) {
            "정규직" -> "FULL_TIME"
            "비정규직" -> "TEMPORARY"
            "계약직" -> "CONSTRACT"
            "인턴" -> "INTERN"
            "병특 희망" -> "HOPE"
            "희망하지 않음" -> "NOT_HOPE"
            "상관없음" -> "NO_MATTER"
            "해당 사항 없음" -> "NONE"
            else -> ""
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