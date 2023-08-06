package com.sms.presentation.main.ui.mypage

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.modal.MyPageBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyPageScreen() {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
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
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        MyPageComponent(clickTopLeftButton = {}, clickTopRightButton = {
            coroutineScope.launch {
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