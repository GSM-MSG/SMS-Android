package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSelectionControlsGroup(
    role: String,
    resetButtonClick: Boolean,
    onResetButtonClickValueChanged: (value: Boolean) -> Unit,
    selectedSchoolNumberAscendingValue: Boolean,
    selectedGsmScoreAscendingValue: Boolean,
    selectedDesiredAnnualSalaryAscendingValue: Boolean,
    onSchoolNumberAscendingValueChanged: (value: Boolean) -> Unit,
    onGsmScoreAscendingValueChanged: (value: Boolean) -> Unit,
    onDesiredAnnualSalaryAscendingValueChanged: (value: Boolean) -> Unit
) {
    val schoolNumberAscending = remember {
        mutableStateOf(selectedSchoolNumberAscendingValue)
    }
    val gsmScoreAscending = remember {
        mutableStateOf(selectedGsmScoreAscendingValue)
    }
    val desiredAnnualSalaryAscending = remember {
        mutableStateOf(selectedDesiredAnnualSalaryAscendingValue)
    }

    onSchoolNumberAscendingValueChanged(schoolNumberAscending.value)
    onGsmScoreAscendingValueChanged(gsmScoreAscending.value)
    onDesiredAnnualSalaryAscendingValueChanged(desiredAnnualSalaryAscending.value)

    if (resetButtonClick) {
        schoolNumberAscending.value = true
        gsmScoreAscending.value = true
        desiredAnnualSalaryAscending.value = true
        onResetButtonClickValueChanged(false)
    }

    if (role != "") {
        FilterSelectionControls(
            title = "학번",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = schoolNumberAscending.value,
            onSelectionClick = {
                schoolNumberAscending.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (role == "ROLE_TEACHER") {
        FilterSelectionControls(
            title = "인증제 점수",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = gsmScoreAscending.value,
            onSelectionClick = {
                gsmScoreAscending.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectionControls(
            title = "희망 연봉",
            firstSelectionName = "오름차순",
            secondSelectionName = "내림차순",
            selectionValue = desiredAnnualSalaryAscending.value,
            onSelectionClick = {
                desiredAnnualSalaryAscending.value = it
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}