package com.sms.presentation.main.ui.mypage

import android.graphics.Bitmap
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.component.military.MilitaryBottomSheet
import com.sms.presentation.main.ui.mypage.component.work.WorkingConditionBottomSheet
import com.sms.presentation.main.ui.mypage.modal.MyPageBottomSheet
import com.sms.presentation.main.ui.mypage.state.ActivityDuration
import com.sms.presentation.main.ui.mypage.state.FormOfEmployment
import com.sms.presentation.main.ui.mypage.state.MilitaryService
import com.sms.presentation.main.ui.mypage.state.MyProfileData
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack
import com.sms.presentation.main.viewmodel.MyProfileViewModel
import kotlinx.coroutines.launch

private enum class BottomSheetValues {
    Major,
    MyPage,
    WorkingForm,
    Military,
    ProjectPicker,
    AwardPicker
}

private enum class ModalValue {
    Withdrawal,
    Logout
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MyPageScreen(
    viewModel: MyProfileViewModel,
    myProfileData: MyProfileData,
    bitmapPreviews: List<List<Bitmap>>,
    projects: List<ProjectData>,
    awards: List<AwardData>,
    majorList: List<String>,
    selectedTechList: List<String>,
    selectedTechListOnProject: List<ProjectTechStack>,
    bitmapIcons: List<Bitmap?>,
    setBitmap: (index: Int, element: Bitmap) -> Unit,
    isExpandedProject: List<Boolean>,
    isExpandedAward: List<Boolean>,
    onExpandProjectClick: (index: Int) -> Unit,
    onExpandAwardClick: (index: Int) -> Unit,
    onChangeProgressState: (index: Int) -> Unit,
    onWithdrawal: () -> Unit,
    onLogout: () -> Unit,
    onAddProject: () -> Unit,
    onAddAward: () -> Unit,
    onChangeProjectDateValue: (index: Int, value: String, isStart: Boolean) -> Unit,
    onChangeAwardDateValue: (index: Int, value: String) -> Unit,
    onClickSearchBar: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickProjectSearchBar: (itemIndex: Int) -> Unit,
    onProfileValueChange: (value: MyProfileData) -> Unit,
    onRemoveDetailStack: (value: String) -> Unit,
    onRemoveProject: (index: Int) -> Unit,
    onRemoveAward: (index: Int) -> Unit,
    onRemoveBitmapPreview: (projectIndex: Int, previewIndex: Int) -> Unit,
    onAddBitmapPreview: (projectIndex: Int, previews: List<Bitmap>) -> Unit,
    onRemoveProjectDetailStack: (index: Int, value: String) -> Unit,
    onProjectValueChange: (index: Int, data: ProjectData) -> Unit,
    onAwardValueChange: (index: Int, award: AwardData) -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetValues = remember {
        mutableStateOf(BottomSheetValues.MyPage)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val dialogState = remember {
        mutableStateOf(ModalValue.Logout)
    }

    val projectIndex = remember {
        mutableStateOf(0)
    }

    val awardIndex = remember {
        mutableStateOf(0)
    }

    val isStartProject = remember {
        mutableStateOf(true)
    }

    val dialogVisibility = remember {
        mutableStateOf(false)
    }

    if (viewModel.isProfileChanged.value && viewModel.isProjectIconChanged.value && viewModel.isProjectPreviewChanged.value) {
        viewModel.putChangeProfile()
    }

    if (dialogVisibility.value) {
        SmsDialog(
            isError = true,
            title = if (dialogState.value == ModalValue.Logout) "로그아웃" else "회원탈퇴",
            msg = "정말로 ${if (dialogState.value == ModalValue.Logout) "로그아웃" else "회원탈퇴"} 하시겠습니까?",
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = {
                dialogVisibility.value = false
            },
            importantButtonOnClick = {
                dialogVisibility.value = true
                if (dialogState.value == ModalValue.Logout) {
                    onLogout()
                } else {
                    onWithdrawal()
                }
            })
    }
    ModalBottomSheetLayout(
        sheetContent = {
            when (bottomSheetValues.value) {
                BottomSheetValues.Major -> {
                    SelectorBottomSheet(
                        itemChange = { viewModel.onChangeMajorValue(it) },
                        list = majorList,
                        bottomSheetState = bottomSheetState,
                        selected = myProfileData.major,
                        lastItem = {
                            MajorSelector(
                                major = "직접입력",
                                selected = myProfileData.major == "직접입력"
                            ) {
                                viewModel.onChangeMajorValue("직접입력")
                                coroutineScope.launch {
                                    bottomSheetState.hide()
                                }
                            }
                        }
                    )
                }

                BottomSheetValues.WorkingForm -> {
                    WorkingConditionBottomSheet(
                        list = listOf(
                            FormOfEmployment.FULL_TIME,
                            FormOfEmployment.TEMPORARY,
                            FormOfEmployment.CONTRACT,
                            FormOfEmployment.INTERN
                        ),
                        itemChange = {
                            onProfileValueChange(
                                myProfileData.copy(
                                    formOfEmployment = FormOfEmployment.valueOf(
                                        it
                                    )
                                )
                            )
                        },
                        bottomSheetState = bottomSheetState,
                        selected = myProfileData.formOfEmployment.name,
                    )
                }

                BottomSheetValues.MyPage -> {
                    MyPageBottomSheet(
                        onClickLogout = {
                            coroutineScope.launch {
                                dialogState.value = ModalValue.Logout
                                bottomSheetState.hide()
                                dialogVisibility.value = true
                            }
                        },
                        onClickWithdrawal = {
                            coroutineScope.launch {
                                dialogState.value = ModalValue.Withdrawal
                                bottomSheetState.hide()
                                dialogVisibility.value = true
                            }
                        })
                }

                BottomSheetValues.Military -> {
                    MilitaryBottomSheet(
                        list = listOf(
                            MilitaryService.HOPE,
                            MilitaryService.NOT_HOPE,
                            MilitaryService.NO_MATTER,
                            MilitaryService.NONE
                        ),
                        itemChange = {
                            onProfileValueChange(
                                myProfileData.copy(
                                    militaryService = MilitaryService.valueOf(
                                        it
                                    )
                                )
                            )
                        },
                        bottomSheetState = bottomSheetState,
                        selected = myProfileData.militaryService.name,
                    )
                }

                BottomSheetValues.ProjectPicker -> {
                    val activityDuration =
                        viewModel.projects.value[projectIndex.value].activityDuration
                    val date = if (isStartProject.value) {
                        activityDuration.start
                    } else {
                        activityDuration.end
                    }
                    SmsDatePicker(modifier = Modifier.height(163.dp),
                        onYearValueChange = {
                        onChangeProjectDateValue(
                            projectIndex.value,
                            date!!.replaceBefore('.', it),
                            isStartProject.value
                        )
                    }, onMonthValueChange = {
                        onChangeProjectDateValue(
                            projectIndex.value,
                            date!!.replaceAfter('.', it),
                            isStartProject.value
                        )
                    })
                }

                BottomSheetValues.AwardPicker -> {
                    SmsDatePicker(modifier = Modifier.height(163.dp),
                        onYearValueChange = {
                        onChangeAwardDateValue(
                            awardIndex.value,
                            viewModel.awards.value[awardIndex.value].date.replaceBefore('.', it)
                        )
                    }, onMonthValueChange = {
                        onChangeAwardDateValue(
                            awardIndex.value,
                            viewModel.awards.value[awardIndex.value].date.replaceBefore('.', it)
                        )
                    })
                }
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        MyPageComponent(
            myProfileData = myProfileData,
            bitmapPreviews = bitmapPreviews,
            projects = projects,
            awards = awards,
            isExpandedProject = isExpandedProject,
            isExpandedAward = isExpandedAward,
            selectedTechListOnProject = selectedTechListOnProject,
            selectedTechList = selectedTechList,
            onExpandProjectClick = onExpandProjectClick,
            onExpandAwardClick = onExpandAwardClick,
            onAddProject = onAddProject,
            onAddAward = onAddAward,
            onAddRegion = { viewModel.addRegion() },
            onAddForeignLanguage = { viewModel.addForeignLanguage() },
            onAddCertificate = { viewModel.addCertificate() },
            onClickTopLeftButton = onClickBackButton,
            onClickOpenWorkForm = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.WorkingForm
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onClickMilitaryOpenButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.Military
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onClickTopRightButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.MyPage
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onClickMajorButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.Major
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onOpenNumberPicker = {
                coroutineScope.launch {
                    awardIndex.value = it
                    bottomSheetValues.value = BottomSheetValues.AwardPicker
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onOpenStart = {
                coroutineScope.launch {
                    isStartProject.value = true
                    projectIndex.value = it
                    bottomSheetValues.value = BottomSheetValues.ProjectPicker
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onOpenEnd = {
                coroutineScope.launch {
                    isStartProject.value = false
                    projectIndex.value = it
                    bottomSheetValues.value = BottomSheetValues.ProjectPicker
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            onEnteredMajorValue = { viewModel.onEnteredMajorValue(it) },
            onMyPageSearchBar = onClickSearchBar,
            onProjectSearchBar = onClickProjectSearchBar,
            onRemoveBitmapPreview = onRemoveBitmapPreview,
            onAddBitmapPreview = onAddBitmapPreview,
            onRemoveDetailStack = onRemoveDetailStack,
            onRemoveProject = onRemoveProject,
            onRemoveAward = onRemoveAward,
            onRemoveProjectDetailStack = { index: Int, value: String ->
                onRemoveProjectDetailStack(index, value)
            },
            onProjectValueChange = onProjectValueChange,
            onAwardValueChange = onAwardValueChange,
            onProfileValueChange = onProfileValueChange,
            onSaveButtonClick = onSaveButtonClick,
            iconBitmaps = bitmapIcons,
            setBitmap = setBitmap,
            onChangeProgressState = onChangeProgressState
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPre() {
    MyPageScreen(
        myProfileData = MyProfileData(
            name = "",
            introduce = "",
            portfolioUrl = "",
            grade = 0,
            classNum = 0,
            number = 0,
            department = "",
            major = "",
            profileImg = "",
            contactEmail = "",
            gsmAuthenticationScore = 0,
            formOfEmployment = FormOfEmployment.FULL_TIME,
            regions = listOf(),
            militaryService = MilitaryService.NOT_SELECT,
            salary = 0,
            languageCertificates = listOf(),
            certificates = listOf(),
            profileImageBitmap = null
        ),
        bitmapPreviews = listOf(),
        isExpandedAward = listOf(),
        isExpandedProject = listOf(),
        projects = listOf(
            ProjectData(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023. 03", end = null),
                projectImage = listOf(
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
                ),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                keyTask = "모이자 ㅋㅋ",
                relatedLinks = listOf(
                    RelatedLinksData("Youtube", "https://dolmc.com"),
                    RelatedLinksData("GitHub", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                ),
                techStacks = listOf("Android", "Kotlin"),
                description = ""
            )
        ),
        awards = listOf(),
        selectedTechListOnProject = listOf(ProjectTechStack(listOf())),
        selectedTechList = listOf("Kotlin", "Flutter"),
        majorList = listOf("BackEnd", "Android", "iOS"),
        onLogout = {},
        onWithdrawal = {},
        onAddProject = {},
        onAddAward = {},
        onClickBackButton = {},
        onClickSearchBar = {},
        onClickProjectSearchBar = {},
        onProfileValueChange = {},
        onRemoveDetailStack = {},
        onRemoveProject = {},
        onRemoveProjectDetailStack = { _, _ -> },
        onProjectValueChange = { _, _ -> },
        onAwardValueChange = { _, _ -> },
        onAddBitmapPreview = { _, _ -> },
        onRemoveBitmapPreview = { _, _ -> },
        onExpandAwardClick = {},
        onExpandProjectClick = {},
        onRemoveAward = {},
        viewModel = viewModel(),
        onSaveButtonClick = {},
        bitmapIcons = listOf(),
        setBitmap = { _, _ -> },
        onChangeAwardDateValue = { _, _ -> },
        onChangeProjectDateValue = { _, _, _ -> },
        onChangeProgressState = {}
    )
}