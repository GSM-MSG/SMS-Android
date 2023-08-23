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
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.mypage.modal.MyPageBottomSheet
import kotlinx.coroutines.launch

private enum class BottomSheetValues {
    Major,
    MyPage,
    WorkingForm,
    Military
}

private enum class ModalValue {
    Withdrawal,
    Logout
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MyPageScreen(
    onWithdrawal: () -> Unit,
    onLogout: () -> Unit,
    onClickSearchBar: () -> Unit,
    onClickProjectSearchBar: (itemIndex: Int) -> Unit,
) {
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
    val selectedMilitary = remember {
        mutableStateOf("")
    }
    val bottomSheetValues = remember {
        mutableStateOf(BottomSheetValues.MyPage)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val dialogState = remember {
        mutableStateOf(ModalValue.Logout)
    }

    val dialogVisibility = remember {
        mutableStateOf(false)
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
                    SelectorBottomSheet(
                        list = listOf("병특 희망", "희망 하지 않음", "상관 없음", "해당 사항 없음"),
                        bottomSheetState = bottomSheetState,
                        selected = selectedMilitary.value,
                        itemChange = { selectedMilitary.value = it }
                    )
                }
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        MyPageComponent(
            setMajor = selectedMajor.value,
            setWantWorkForm = selectedWorkForm.value,
            setMilitary = selectedMilitary.value,
            onClickTopLeftButton = {},
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
            }, onClickMajorButton = {
                coroutineScope.launch {
                    bottomSheetValues.value = BottomSheetValues.Major
                    keyboardController!!.hide()
                    bottomSheetState.show()
                }
            }, onMyPageSearchBar = onClickSearchBar,
            onProjectSearchBar = onClickProjectSearchBar
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPre() {
    MyPageScreen(
        onLogout = {},
        onWithdrawal = {},
        onClickSearchBar = {},
        onClickProjectSearchBar = {}
    )
}