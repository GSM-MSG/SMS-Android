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
    val progressState = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainScreenTopBar(
            profileImageUrl = "",
            filterButtonOnClick = { /*TODO (KimHyunseung) : 필터 Screen으로 이동*/ },
            profileButtonOnClick = { /*TODO (KimHyunseung) : 마이페이지로 이동*/ }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            StudentListComponent(
                listState = listState,
                progressState = progressState.value,
                studentList = viewModel.getStudentList()
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
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filter { it == listState.layoutInfo.totalItemsCount - 1 }
            .collect {
                viewModel.getStudentList(
                    viewModel.getStudentListResponse.value.data!!.page + 1,
                    20
                )
                getStudentList(viewModel = viewModel) {
                    progressState.value = it
                }
            }
    }
}

suspend fun getStudentList(
    viewModel: StudentListViewModel,
    progressState: (Boolean) -> Unit
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