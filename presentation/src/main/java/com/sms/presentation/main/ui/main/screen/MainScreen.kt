package com.sms.presentation.main.ui.main.screen

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ListFloatingButton
import com.msg.sms.domain.model.student.response.StudentModel
import com.sms.presentation.main.ui.detail.StudentDetailScreen
import com.sms.presentation.main.ui.main.component.LogoutWithDrawalBottomSheetComponent
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
) {
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

    LaunchedEffect("GetStudentList") {
        getStudentList(viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list, size ->
                studentList.value += list
                listTotalSize.value = size
            })
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
                StudentDetailScreen(onDismissButtonClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                })
            } else {
                LogoutWithDrawalBottomSheetComponent(
                    onLogoutClick = {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                        dialogState.value = true
                        dialogTitle.value = "로그아웃"
                        dialogMsg.value = "정말로 로그아웃 하시겠습니까?"
                        dialogOnClick.value = {
                            viewModel.logout()
                        }
                    },
                    onWithDrawalClick = {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                        dialogState.value = true
                        dialogTitle.value = "회원탈퇴"
                        dialogMsg.value = "정말로 회원탈퇴 하시겠습니까?"
                        dialogOnClick.value = {
                            viewModel.withdrawal()
                        }
                    }
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
            MainScreenTopBar(
                profileImageUrl = "",
                isScolled = isScrolled.value,
                filterButtonOnClick = { /*TODO (KimHyunseung) : 필터 Screen으로 이동*/ },
                profileButtonOnClick = {
                    isDetailBottomSheet.value = false
                    scope.launch {
                        bottomSheetState.show()
                    }
                }
            )
            Box(modifier = Modifier.fillMaxSize()) {
                StudentListComponent(
                    listState = listState,
                    progressState = progressState.value,
                    studentList = studentList.value,
                    listTotalSize = listTotalSize.value
                ) {
                    isDetailBottomSheet.value = true
                    scope.launch {
                        bottomSheetState.animateTo(targetValue = ModalBottomSheetValue.Expanded)
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