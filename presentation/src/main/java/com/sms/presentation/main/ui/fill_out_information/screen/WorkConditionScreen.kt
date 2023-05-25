package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.WorkConditionComponent
import com.sms.presentation.main.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkConditionScreen(
    navController: NavController,
    viewModel: StudentViewModel,
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val selectedWorkingCondition = remember { mutableStateOf("") }
    val workingConditionList = listOf("정규직", "비정규직", "계약직", "인턴")

    val data = viewModel.getEnteredWorkConditionInformation()

    ModalBottomSheetLayout(
        sheetContent = {
            SelectorBottomSheet(
                list = workingConditionList,
                bottomSheetState = bottomSheetState,
                selected = if (selectedWorkingCondition.value == "") data.formOfEmployment else selectedWorkingCondition.value,
                itemChange = { selectedWorkingCondition.value = it },
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            TopBarComponent(text = "정보 입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {
            }
            SmsSpacer()
            WorkConditionComponent(
                bottomSheetState = bottomSheetState,
                wantWorkingCondition = if (selectedWorkingCondition.value == "") data.formOfEmployment else selectedWorkingCondition.value,
                navController = navController,
                data = data,
                viewModel = viewModel
            )
        }
    }
}