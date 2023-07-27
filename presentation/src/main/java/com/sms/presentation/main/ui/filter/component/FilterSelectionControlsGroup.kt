package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.viewmodel.StudentListViewModel

@Composable
fun FilterSelectionControlsGroup(role: String, viewModel: StudentListViewModel) {
    if (role != "") {
        FilterSelectionControls(
            title = "학번",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = viewModel.isSchoolNumberAscendingOrder.value,
            onSelectionClick = {
                viewModel.isSchoolNumberAscendingOrder.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (role == "ROLE_TEACHER") {
        FilterSelectionControls(
            title = "인증제 점수",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = viewModel.isGsmScoreAscendingOrder.value,
            onSelectionClick = {
                viewModel.isGsmScoreAscendingOrder.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectionControls(
            title = "희망 연봉",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = viewModel.isDesiredAnnualSalaryAscendingOrder.value,
            onSelectionClick = {
                viewModel.isDesiredAnnualSalaryAscendingOrder.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}