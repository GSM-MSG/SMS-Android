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

enum class BottomSheetValues {
    Major,
    MyPage,
    WorkingForm,
    Military
}

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
    val selectedWorkForm = remember {
        mutableStateOf("정규직")
    }
    val bottomSheetValues = remember {
        mutableStateOf(BottomSheetValues.MyPage)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    ModalBottomSheetLayout(
        sheetContent = {
            when (bottomSheetValues.value) {
                BottomSheetValues.Major -> {
                    SelectorBottomSheet(
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
                }

                BottomSheetValues.WorkingForm -> {
                    SelectorBottomSheet(
                        list = listOf("정규직", "비정규직", "계약직", "인턴"),
                        bottomSheetState = bottomSheetState,
                        selected = selectedWorkForm.value,
                        itemChange = { selectedWorkForm.value = it }
                    )
                }

                BottomSheetValues.MyPage -> {
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

                else -> {}
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        MyPageComponent(
            setMajor = selectedMajor.value,
            setWantWorkForm = selectedWorkForm.value,
            clickTopLeftButton = {},
            onClickOpenWorkForm = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.WorkingForm
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            },
            clickTopRightButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.MyPage
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            }, onClickMajorButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.Major
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