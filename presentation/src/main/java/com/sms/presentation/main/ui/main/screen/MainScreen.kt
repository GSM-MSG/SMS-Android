package com.sms.presentation.main.ui.main.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.bottomsheet.LogoutWithDrawalBottomSheet
import com.msg.sms.design.component.button.ListFloatingButton
import com.msg.sms.design.component.snackbar.SmsSnackBar
import com.msg.sms.design.icon.ExclamationMarkIcon
import com.msg.sms.domain.model.student.response.GetStudentForAnonymous
import com.msg.sms.domain.model.student.response.GetStudentForStudent
import com.msg.sms.domain.model.student.response.GetStudentForTeacher
import com.msg.sms.domain.model.student.response.StudentModel
import com.sms.presentation.main.ui.detail.StudentDetailScreen
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.MainActivity
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.ui.main.data.StudentDetailData
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
    val context = LocalContext.current as MainActivity
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val studentList = remember {
        mutableStateOf(listOf<StudentModel>())
    }
    val progressState = remember {
        mutableStateOf(false)
    }
    val listTotalSize = remember {
        mutableStateOf(0)
    }
    val isScrolled = remember {
        mutableStateOf(false)
    }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
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
    val isDetailBottomSheet = remember {
        mutableStateOf(false)
    }
    val studentDetailData = remember {
        mutableStateOf(StudentDetailData())
    }
    val profileImageUrl = remember {
        mutableStateOf("")
    }
    val snackBarVisible = remember {
        mutableStateOf(false)
    }

    LaunchedEffect("GetStudentList") {
        getStudentList(viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list, size ->
                studentList.value += list
                listTotalSize.value = size
            })
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

    LaunchedEffect("Pagination") {
        val response = viewModel.getStudentListResponse.value

        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }.filter { it == listState.layoutInfo.totalItemsCount - 1 }
            .collect {
                val isSuccess = response is Event.Success
                if (isSuccess && it != 0) {
                    val isIncompleteData = studentList.value.size < response.data!!.totalSize
                    if (isIncompleteData) {
                        viewModel.getStudentListRequest(response.data.page + 1, 20)
                    }
                    Log.d("pagination", it.toString())
                }
            }
    }

    if (dialogState.value) {
        SmsDialog(
            title = dialogTitle.value,
            msg = dialogMsg.value,
            outLineButtonText = "확인",
            normalButtonText = "취소",
            outlineButtonOnClick = {
                dialogOnClick.value()
                dialogState.value = false
            },
            normalButtonOnClick = {
                dialogState.value = false
            }
        )
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (isDetailBottomSheet.value) {
                StudentDetailScreen(
                    studentDetailData = studentDetailData.value,
                    role = role,
                    onDismissButtonClick = {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                    }
                )
            } else {
                LogoutWithDrawalBottomSheet(
                    onLogoutClick = {
                        dialogState.value = true
                        dialogTitle.value = "로그아웃"
                        dialogMsg.value = "정말로 로그아웃 하시겠습니까?"
                        dialogOnClick.value = {
                            viewModel.logout()
                        }
                    },
                    onWithDrawalClick = {
                        dialogState.value = true
                        dialogTitle.value = "회원탈퇴"
                        dialogMsg.value = "정말로 회원탈퇴 하시겠습니까?"
                        dialogOnClick.value = {
                            viewModel.withdrawal()
                        }
                    },
                    coroutineScope = scope,
                    bottomSheetState = bottomSheetState
                )
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = if (isDetailBottomSheet.value) 0.dp else 16.dp,
            topEnd = if (isDetailBottomSheet.value) 0.dp else 16.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box {
                MainScreenTopBar(
                    profileImageUrl = profileImageUrl.value,
                    isScolled = isScrolled.value,
                    filterButtonOnClick = {
                        /*TODO (KimHyunseung) : 필터 Screen으로 이동*/
                        scope.launch {
                            snackBarVisible.value = true
                            delay(1.5.seconds)
                            if (snackBarVisible.value) snackBarVisible.value = false
                        }
                    },
                    profileButtonOnClick = {
                        if (role == "ROLE_TEACHER" || role == "ROLE_STUDENT") {
                            isDetailBottomSheet.value = false
                            scope.launch {
                                bottomSheetState.show()
                            }
                        } else {
                            context.startActivity(Intent(context, LoginActivity::class.java))
                            context.finish()
                        }
                    }
                )
                SmsSnackBar(
                    text = "아직 개발 중인 기능입니다.",
                    modifier = Modifier.align(Alignment.Center),
                    visible = snackBarVisible.value,
                    leftIcon = { ExclamationMarkIcon() }
                ) {
                    snackBarVisible.value = false
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                StudentListComponent(
                    listState = listState,
                    progressState = progressState.value,
                    studentList = studentList.value,
                    listTotalSize = listTotalSize.value
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
                                        isDetailBottomSheet.value = true
                                        studentDetailData.value = StudentDetailData(
                                            name = it.name,
                                            introduce = it.introduce,
                                            dreamBookFileUrl = it.dreamBookFileUrl!!,
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
                                            techStacks = it.techStacks
                                        )
                                        scope.launch {
                                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
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
                                        isDetailBottomSheet.value = true
                                        studentDetailData.value = StudentDetailData(
                                            name = it.name,
                                            introduce = it.introduce,
                                            grade = it.grade,
                                            classNum = it.classNum,
                                            number = it.number,
                                            department = it.department,
                                            major = it.major,
                                            profileImg = it.profileImg,
                                            techStacks = it.techStack
                                        )
                                        scope.launch {
                                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
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
                                        isDetailBottomSheet.value = true
                                        studentDetailData.value = StudentDetailData(
                                            name = it.name,
                                            introduce = it.introduce,
                                            major = it.major,
                                            profileImg = it.profileImg,
                                            techStacks = it.techStack
                                        )
                                        scope.launch {
                                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
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
    }
}

suspend fun getStudentList(
    viewModel: StudentListViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (studentList: List<StudentModel>, totalListSize: Int) -> Unit,
) {
    viewModel.getStudentListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                progressState(false)
                onSuccess(response.data!!.content, response.data.totalSize)
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
    onSuccess: (GetStudentForTeacher) -> Unit
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
    onSuccess: (GetStudentForStudent) -> Unit
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
    onSuccess: (GetStudentForAnonymous) -> Unit
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
    onSuccess: (profileImageUrl: String) -> Unit
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