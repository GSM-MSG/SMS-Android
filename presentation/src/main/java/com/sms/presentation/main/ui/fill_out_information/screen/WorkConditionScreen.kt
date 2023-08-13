package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.workcondition.WorkConditionComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkConditionScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit
) {
    val selectedWorkingCondition = remember { mutableStateOf("") }
    val workingConditionList = listOf("정규직", "비정규직", "계약직", "인턴")
    val data = viewModel.getEnteredWorkConditionInformation()

    bottomSheetContent(
        content = {
            SelectorBottomSheet(
                list = workingConditionList,
                bottomSheetState = bottomSheetState,
                selected = if (selectedWorkingCondition.value == "") data.formOfEmployment else selectedWorkingCondition.value,
                itemChange = { selectedWorkingCondition.value = it },
            )
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        SmsSpacer()
        WorkConditionComponent(
            bottomSheetState = bottomSheetState,
            wantWorkingCondition = if (selectedWorkingCondition.value == "") data.formOfEmployment else selectedWorkingCondition.value.filter { it.toString() != "" },
            navController = navController,
            data = data,
            viewModel = viewModel
        )
    }
}