package com.sms.presentation.main.ui.fill_out_information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.bottomsheet.MajorBottomSheet
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.ProfileComponent

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val selectedMajor = remember {
        mutableStateOf("FrontEnd")
    }

    val list = listOf(
        "FrontEnd",
        "BackEnd",
        "Android",
        "iOS",
        "Game",
        "Cyber Security",
        "Design",
        "AI",
        "IoT"
    )
    ModalBottomSheetLayout(
        sheetContent = {
            MajorBottomSheet(
                list = list,
                bottomSheetState = bottomSheetState,
                selectedMajor = selectedMajor.value,
                itemChange = {
                    selectedMajor.value = it
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState,
    ) {
        Column {
            TopBarComponent(
                text = "정보입력",
                leftIcon = { BackButtonIcon() },
                rightIcon = null,
                onClickLeftButton = {

                }) {
            }
            SmsSpacer()
            Column(Modifier.verticalScroll(scrollState)) {
                ProfileComponent(bottomSheetState, selectedMajor.value)
            }
        }
    }
}