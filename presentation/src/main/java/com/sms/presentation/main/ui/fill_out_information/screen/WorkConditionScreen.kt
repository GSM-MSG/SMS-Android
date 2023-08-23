package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.workcondition.WorkConditionComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun WorkConditionScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    selectedWorkingCondition: String,
    onWorkingConditionBottomSheetOpenButtonClick: () -> Unit,
) {
    val data = viewModel.getEnteredWorkConditionInformation()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        SmsSpacer()
        WorkConditionComponent(
            wantWorkingCondition = if (selectedWorkingCondition == "") data.formOfEmployment else selectedWorkingCondition.filter { it.toString() != "" },
            navController = navController,
            data = data,
            viewModel = viewModel,
            onWorkingConditionBottomSheetOpenButtonClick = onWorkingConditionBottomSheetOpenButtonClick
        )
    }
}