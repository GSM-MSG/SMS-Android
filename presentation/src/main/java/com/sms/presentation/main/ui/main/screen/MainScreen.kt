package com.sms.presentation.main.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ListFloatingButton
import com.msg.sms.domain.model.student.response.StudentModel
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

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

    LaunchedEffect("GetStudentList") {
        getStudentList(
            viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list, size ->
                studentList.value += list
                listTotalSize.value = size
            }
        )
    }

    LaunchedEffect("isScrolled") {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.first().index != 0 }
            .collect {
                if (isScrolled.value != it)
                    isScrolled.value = it
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainScreenTopBar(
            profileImageUrl = "",
            isScolled = isScrolled.value,
            filterButtonOnClick = { /*TODO (KimHyunseung) : 필터 Screen으로 이동*/ },
            profileButtonOnClick = { /*TODO (KimHyunseung) : 마이페이지로 이동*/ }
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

    LaunchedEffect("Pagination") {
        val response = viewModel.getStudentListResponse.value

        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filter { it == listState.layoutInfo.totalItemsCount - 1 }
            .collect {
                val isSuccess = response is Event.Success
                if (isSuccess) {
                    val isIncompleteData =
                        response.data!!.last
                    if (!isIncompleteData) {
                        viewModel.getStudentListRequest(response.data.page + 1, 20)
                    }
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