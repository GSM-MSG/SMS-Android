package com.sms.presentation.main.ui.mypage

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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.mypage.modal.MyPageBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MyPageScreen() {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val list = remember {
        mutableStateOf(listOf("Android", "iOS", "Back-End"))
    }
    val selectedMajor = remember {
        mutableStateOf("")
    }
    val isMajor = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    ModalBottomSheetLayout(
        sheetContent = {
            if (isMajor.value) {
                SelectorBottomSheet(
                    // Todo(LeeHyeonbin): 바텀 시트 불러오는 로직 사용해서 리스트 열 때나, 언제든 예외처리 하기
//                    list = if (list.value.data != null) list.value.data!!.major else emptyList(),
                    list = list.value,
                    bottomSheetState = bottomSheetState,
                    selected = selectedMajor.value,
                    itemChange = {
                        selectedMajor.value = it
                    },
                    lastItem = {
                        MajorSelector(
                            major = "직접입력",
                            selected = selectedMajor.value == "직접입력"
                        ) {
                            selectedMajor.value = "직접입력"
                            coroutineScope.launch {
                                bottomSheetState.hide()
                            }
                        }
                    }
                )
            } else {
                MyPageBottomSheet(
                    onClickLogout = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    onClickWithdrawal = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    })
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        MyPageComponent(
            setMajor = selectedMajor.value,
            clickTopLeftButton = {},
            clickTopRightButton = {
                coroutineScope.launch {
                    isMajor.value = false
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            }, onClickMajorButton = {
                coroutineScope.launch {
                    isMajor.value = true
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            })
    }
}

@Preview
@Composable
private fun MyPageScreenPre() {
    MyPageScreen()
}