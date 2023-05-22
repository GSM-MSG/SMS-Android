package com.sms.presentation.main.ui.fill_out_information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.MilitaryServiceComponent

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MilitaryServiceScreen() {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val selectedMilitaryService = remember {
        mutableStateOf("")
    }
    val militaryServiceServiceList = listOf("병특 희망", "희망하지 않음", "상관없음", "해당 사항 없음")
    ModalBottomSheetLayout(
        sheetContent = {
            SelectorBottomSheet(
                list = militaryServiceServiceList,
                bottomSheetState = bottomSheetState,
                selected = selectedMilitaryService.value,
                itemChange = { selectedMilitaryService.value = it }
            )
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopBarComponent(text = "정보입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

            }
            SmsSpacer()
            MilitaryServiceComponent(
                bottomSheetState = bottomSheetState,
                selectedMilitaryService = selectedMilitaryService.value
            )
        }
    }

}