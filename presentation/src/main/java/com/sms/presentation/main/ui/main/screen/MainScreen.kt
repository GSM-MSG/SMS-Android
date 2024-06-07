package com.sms.presentation.main.ui.main.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ListFloatingButton
import com.msg.sms.design.component.snackbar.SmsSnackBar
import com.msg.sms.design.icon.CheckedIcon
import com.msg.sms.domain.model.student.response.GetStudentForAnonymousModel
import com.msg.sms.domain.model.student.response.GetStudentForStudentModel
import com.msg.sms.domain.model.student.response.GetStudentForTeacherModel
import com.sms.presentation.main.ui.detail.StudentDetailScreen
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.ui.main.data.StudentDetailData
import com.sms.presentation.main.ui.mypage.state.ActivityDuration
import com.sms.presentation.main.viewmodel.MyProfileViewModel
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    myProfileVIewModel: MyProfileViewModel,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String,
    onFilterClick: () -> Unit,
    onProfileClick: (role: String) -> Unit,
    onClickBackPressed: () -> Unit,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val progressState = remember {
        mutableStateOf(false)
    }
    val isScrolled = remember {
        mutableStateOf(false)
    }
    val bottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    val dialogState = remember {
        mutableStateOf(false)
    }
    val dialogTitle = remember {
        mutableStateOf("")
    }
    val dialogMsg = remember {
        mutableStateOf("")
    }
    val dialogOnClick = remember {
        mutableStateOf({})
    }
    val studentDetailData = remember {
        mutableStateOf(StudentDetailData())
    }
    val profileImageUrl = remember {
        mutableStateOf("")
    }
    val snackBarVisibility = remember {
        mutableStateOf(false)
    }
    val lastVisibleItem = remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
    }

    BackHandler {
        if (bottomSheetState.isVisible) {
            scope.launch {
                bottomSheetState.hide()
            }
        } else {
            onClickBackPressed()
        }
    }

    val putProfileChange = myProfileVIewModel.putChangedProfileResponse.collectAsState()
    if (putProfileChange.value is Event.Success) {
        LaunchedEffect(putProfileChange.value) {
            scope.launch {
                snackBarVisibility.value = true
                delay(3000)
                snackBarVisibility.value = false
                myProfileVIewModel.changeProfileState()
            }
        }
    }

    LaunchedEffect("observeGetStudentListResponse") {
        observeGetStudentListResponse(viewModel) {
            progressState.value = it
        }
    }

    LaunchedEffect("GetProfileImage") {
        if (role == "ROLE_TEACHER" || role == "ROLE_STUDENT") {
            viewModel.getProfileImageUrl()
            getUserProfileImageUrl(
                viewModel = viewModel,
                onSuccess = {
                    profileImageUrl.value = it
                }
            )
        }
    }

    LaunchedEffect("isScrolled") {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.first().index != 0 }.collect {
            if (isScrolled.value != it) isScrolled.value = it
        }
    }

    LaunchedEffect(lastVisibleItem.value) {
        if (lastVisibleItem.value == viewModel.studentList.lastIndex && viewModel.getStudentListResponse.value.data?.last != true) {
            viewModel.getStudentListRequest(
                viewModel.getStudentListResponse.value.data!!.page + 1,
                20
            )
        }
    }

    if (dialogState.value) {
        SmsDialog(
            title = dialogTitle.value,
            msg = dialogMsg.value,
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = {
                dialogState.value = false
            },
            importantButtonOnClick = {
                dialogOnClick.value()
                dialogState.value = false
            }
        )
    }

    ModalBottomSheetLayout(
        sheetContent = {
            StudentDetailScreen(
                studentDetailData = studentDetailData.value,
                role = role,
                onDismissButtonClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }
            )
        },
        sheetState = bottomSheetState,
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Box {
                    MainScreenTopBar(
                        profileImageUrl = profileImageUrl.value,
                        isScolled = isScrolled.value,
                        filterButtonOnClick = onFilterClick,
                        profileButtonOnClick = {
                            when (role) {
                                "ROLE_STUDENT" -> onProfileClick(role)
                                "ROLE_TEACHER" -> {
                                    dialogTitle.value = "게스트모드 종료"
                                    dialogMsg.value = "정말로 게스트 모드를 종료하시겠습니까?"
                                    dialogOnClick.value = { onProfileClick(role) }
                                    dialogState.value = true
                                }

                                else -> {
                                    dialogTitle.value = "게스트모드 종료"
                                    dialogMsg.value = "정말로 게스트 모드를 종료하시겠습니까?"
                                    dialogOnClick.value = { onProfileClick(role) }
                                    dialogState.value = true
                                }
                            }
                        }
                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    StudentListComponent(
                        listState = listState,
                        progressState = progressState.value,
                        studentList = viewModel.studentList,
                        listTotalSize = viewModel.studentList.size
                    ) {
                        lifecycleScope.launch {
                            when (role) {
                                "ROLE_TEACHER" -> {
                                    viewModel.getStudentDetailForTeacher(it)
                                    getStudentDetailForTeacher(
                                        viewModel,
                                        { state, title, msg ->
                                            dialogState.value = state
                                            dialogTitle.value = title
                                            dialogMsg.value = msg
                                        },
                                        {
                                            studentDetailData.value = StudentDetailData(
                                                name = it.name,
                                                introduce = it.introduce,
                                                portfolioUrl = it.portfolioUrl!!,
                                                grade = it.grade,
                                                classNum = it.classNum,
                                                number = it.number,
                                                department = it.department,
                                                major = it.major,
                                                profileImg = it.profileImg,
                                                contactEmail = it.contactEmail,
                                                gsmAuthenticationScore = it.gsmAuthenticationScore,
                                                formOfEmployment = it.formOfEmployment,
                                                regions = it.regions,
                                                militaryService = it.militaryService,
                                                salary = it.salary,
                                                languageCertificates = it.languageCertificates,
                                                certificates = it.certificates,
                                                techStacks = it.techStacks,
                                                projectList = it.projects.map { model ->
                                                    ProjectData(
                                                        name = model.name,
                                                        activityDuration = ActivityDuration(
                                                            model.inProgress.start,
                                                            model.inProgress.end
                                                        ),
                                                        description = model.description,
                                                        icon = model.icon,
                                                        keyTask = model.myActivity,
                                                        projectImage = model.previewImages,
                                                        relatedLinks = model.links.map { link ->
                                                            RelatedLinksData(link.name, link.url)
                                                        },
                                                        techStacks = model.techStacks
                                                    )
                                                },
                                                awardData = it.prizes.map { model ->
                                                    AwardData(
                                                        title = model.name,
                                                        organization = model.type,
                                                        date = model.date
                                                    )
                                                }
                                            )
                                            scope.launch {
                                                bottomSheetState.show()
                                            }
                                        }
                                    )
                                }

                                "ROLE_STUDENT" -> {
                                    viewModel.getStudentDetailForStudent(it)
                                    getStudentDetailForStudent(
                                        viewModel,
                                        { state, title, msg ->
                                            dialogState.value = state
                                            dialogTitle.value = title
                                            dialogMsg.value = msg
                                        },
                                        {
                                            studentDetailData.value = StudentDetailData(
                                                name = it.name,
                                                introduce = it.introduce,
                                                grade = it.grade,
                                                classNum = it.classNum,
                                                number = it.number,
                                                department = it.department,
                                                major = it.major,
                                                profileImg = it.profileImg,
                                                techStacks = it.techStack,
                                                projectList = it.projects.map { model ->
                                                    ProjectData(
                                                        name = model.name,
                                                        activityDuration = ActivityDuration(
                                                            model.inProgress.start,
                                                            model.inProgress.end
                                                        ),
                                                        description = model.description,
                                                        icon = model.icon,
                                                        keyTask = model.myActivity,
                                                        projectImage = model.previewImages,
                                                        relatedLinks = model.links.map { link ->
                                                            RelatedLinksData(link.name, link.url)
                                                        },
                                                        techStacks = model.techStacks
                                                    )
                                                },
                                                awardData = it.prizes.map { model ->
                                                    AwardData(
                                                        title = model.name,
                                                        organization = model.type,
                                                        date = model.date
                                                    )
                                                }
                                            )
                                            scope.launch {
                                                bottomSheetState.show()
                                            }
                                        }
                                    )
                                }

                                else -> {
                                    viewModel.getStudentDetailForAnonymous(it)
                                    getStudentDetailForAnonymous(
                                        viewModel,
                                        { state, title, msg ->
                                            dialogState.value = state
                                            dialogTitle.value = title
                                            dialogMsg.value = msg
                                        },
                                        {
                                            studentDetailData.value = StudentDetailData(
                                                name = it.name,
                                                introduce = it.introduce,
                                                major = it.major,
                                                techStacks = it.techStack,
                                                awardData = it.awardData.map { prize ->
                                                    AwardData(
                                                        title = prize.name,
                                                        date = prize.date,
                                                        organization = prize.type
                                                    )
                                                },
                                                projectList = it.projectList.map { model ->
                                                    ProjectData(
                                                        name = model.name,
                                                        activityDuration = ActivityDuration(
                                                            model.inProgress.start,
                                                            model.inProgress.end
                                                        ),
                                                        description = model.description,
                                                        icon = model.icon,
                                                        keyTask = model.myActivity,
                                                        projectImage = model.previewImages,
                                                        relatedLinks = model.links.map { link ->
                                                            RelatedLinksData(link.name, link.url)
                                                        },
                                                        techStacks = model.techStacks
                                                    )
                                                }
                                            )
                                            scope.launch {
                                                bottomSheetState.show()
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 32.dp, end = 20.dp)
                    ) {
                        ListFloatingButton(onClick = {
                            scope.launch {
                                listState.animateScrollToItem(0)
                            }
                        })
                    }
                }
            }
            SmsSnackBar(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "정보 기입이 완료되었습니다.",
                visible = snackBarVisibility.value,
                leftIcon = { CheckedIcon() }) {
                snackBarVisibility.value = false
            }
        }
    }
}

suspend fun observeGetStudentListResponse(
    viewModel: StudentListViewModel,
    progressState: (Boolean) -> Unit,
) {
    viewModel.getStudentListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                progressState(false)
            }

            is Event.Loading -> {
                progressState(true)
            }

            else -> {
                progressState(false)
            }
        }
    }
}

suspend fun getStudentDetailForTeacher(
    viewModel: StudentListViewModel,
    dialog: (dialogState: Boolean, dialogTitle: String, dialogMsg: String) -> Unit,
    onSuccess: (GetStudentForTeacherModel) -> Unit,
) {
    viewModel.getStudentDetailForTeacherResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            is Event.Loading -> {}
            else -> {
                dialog(true, "에러", "알 수 없는 에러 발생")
            }
        }
    }
}

suspend fun getStudentDetailForStudent(
    viewModel: StudentListViewModel,
    dialog: (dialogState: Boolean, dialogTitle: String, dialogMsg: String) -> Unit,
    onSuccess: (GetStudentForStudentModel) -> Unit,
) {
    viewModel.getStudentDetailForStudentResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            is Event.Loading -> {}
            else -> {
                dialog(true, "에러", "알 수 없는 에러 발생")
            }
        }
    }
}

suspend fun getStudentDetailForAnonymous(
    viewModel: StudentListViewModel,
    dialog: (dialogState: Boolean, dialogTitle: String, dialogMsg: String) -> Unit,
    onSuccess: (GetStudentForAnonymousModel) -> Unit,
) {
    viewModel.getStudentDetailForAnonymousResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            is Event.Loading -> {}
            else -> {
                dialog(true, "에러", "알 수 없는 에러 발생")
            }
        }
    }
}

suspend fun getUserProfileImageUrl(
    viewModel: StudentListViewModel,
    onSuccess: (profileImageUrl: String) -> Unit,
) {
    viewModel.getStudentProfileImageResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!.profileImgUrl)
            }

            else -> {}
        }
    }
}