package com.sms.presentation.main.ui.detail_stack_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.button.SmsBoxButton
import com.msg.sms.design.component.snackbar.TechStackSnackBar
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.SearchTopBar
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun DetailStackSearchScreen(
    navController: NavController,
    viewModel: SearchDetailStackViewModel,
) {
    val searchQuery = remember {
        mutableStateOf("")
    }
    val detailStack =
        viewModel.searchResult.collectAsState()

    val selectedStack = remember {
        mutableStateListOf<String>()
    }
    val snackBarVisible = remember {
        mutableStateOf(false)
    }
    val snackBarAdded = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val nextButtonText =
        ("세부 스택 ${if (selectedStack.isEmpty()) "" else "${selectedStack.size}개 "}추가")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SearchTopBar(
                setText = searchQuery.value,
                onValueChanged = { searchQuery.value = it },
                onClickButton = { searchQuery.value = "" },
                onClickBackButton = { navController.popBackStack() },
                debounceTime = 300L,
                debounceTextChanged = { if (it != "") viewModel.searchDetailStack(name = it) }
            )
            TechStackSnackBar(
                modifier = Modifier.align(Alignment.Center),
                visible = snackBarVisible.value,
                added = snackBarAdded.value
            ) {
                snackBarVisible.value = false
            }
        }
        SmsSpacer()
        RecentlyAddedListComponent(
            modifier = Modifier.weight(1f),
            list = if(detailStack.value.data != null) detailStack.value.data!!.techStack else emptyList(),
            selectedList = selectedStack,
            onClickRemoveAll = { selectedStack.clear() },
            isSearching = searchQuery.value != "",
            onClickButton = { stack, checked ->
                snackBarAdded.value = !checked
                if (checked) {
                    selectedStack.remove(stack)
                } else {
                    selectedStack.add(stack)
                }
                scope.launch {
                    snackBarVisible.value = true
                    delay(1.5.seconds)
                    if (snackBarVisible.value) snackBarVisible.value = false
                }
            }
        )
        SmsBoxButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = nextButtonText,
            enabled = selectedStack.isNotEmpty()
        ) {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                key = "detailStack",
                value = selectedStack.joinToString(",")
            )
            navController.navigate("Profile")
        }
    }
}

@Preview
@Composable
fun DetailStackSearchScreenPre() {
    DetailStackSearchScreen(navController = rememberNavController(), viewModel = viewModel())
}
