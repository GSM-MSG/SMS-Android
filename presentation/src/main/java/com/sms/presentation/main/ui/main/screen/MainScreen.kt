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
import com.msg.sms.design.component.button.ListFloatingButton
import com.msg.sms.design.icon.RedLogoutIcon
import com.msg.sms.design.icon.RedWithdrawalIcon
import com.msg.sms.domain.model.student.response.StudentModel
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.ModalBottomSheetItem
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: StudentListViewModel
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

    ModalBottomSheetLayout(
        sheetContent = {
            Spacer(modifier = Modifier.height(24.dp))
            ModalBottomSheetItem(
                text = "로그아웃",
                icon = {
                    RedLogoutIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                }
            ) {
                /*TODO(Kimhyunseung) - 로그아웃 */
            }
            Spacer(modifier = Modifier.height(8.dp))
            ModalBottomSheetItem(
                text = "회원탈퇴",
                icon = {
                    RedWithdrawalIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                }
            ) {
                /*TODO(Kimhyunseung) - 회원탈퇴 */
            }
            Spacer(modifier = Modifier.height(16.dp))
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
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
                    //TODO (Kimhyunseung) : 디테일 페이지로 이동
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
}

suspend fun getStudentList(
    viewModel: StudentListViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (studentList: List<StudentModel>, totalListSize: Int) -> Unit
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