package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.viewmodel.StudentListViewModel

@Composable
fun FilterSliderGroup(role: String, viewModel: StudentListViewModel) {
    if (role == "ROLE_TEACHER") {
        FilterSliderComponent(
            title = "인증제 점수",
            sliderValue = viewModel.gsmScoreSliderValues.value,
            valueRange = 0f..990f,
            onSliderValueChange = { viewModel.gsmScoreSliderValues.value = it },
            startValue = viewModel.gsmScoreSliderValues.value.start.toInt().toString(),
            endValue = viewModel.gsmScoreSliderValues.value.endInclusive.toInt().toString(),
            onStartValueChange = {
                val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                val startValue = viewModel.gsmScoreSliderValues.value.start
                val endValue = viewModel.gsmScoreSliderValues.value.endInclusive

                viewModel.gsmScoreSliderValues.value = when (inputValue) {
                    0f -> if (startValue.toInt() == 0) 0f else inputValue
                    in 0f..endValue -> if (startValue.toInt() == 0) it.replace(
                        "0",
                        ""
                    )
                        .toFloat() else inputValue
                    else -> endValue
                }..endValue
            },
            onEndValueChange = {
                val inputValue = it.toFloatOrNull() ?: 0f
                val startValue = viewModel.gsmScoreSliderValues.value.start
                val endValue = viewModel.gsmScoreSliderValues.value.endInclusive

                viewModel.gsmScoreSliderValues.value = when {
                    inputValue in startValue..990f -> {
                        val updatedStartValue = when {
                            inputValue == 0f && endValue.toInt() == 0 -> 0f
                            inputValue != 0f && endValue.toInt() == 0 -> inputValue.toInt()
                                .toString().replace("0", "").toFloat()
                            else -> inputValue
                        }
                        startValue..updatedStartValue
                    }
                    inputValue > endValue -> startValue..990f
                    else -> startValue..startValue
                }
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSliderComponent(
            title = "희망연봉",
            isHopeSalary = true,
            sliderValue = viewModel.desiredAnnualSalarySliderValues.value,
            valueRange = 0f..9999f,
            onSliderValueChange = { viewModel.desiredAnnualSalarySliderValues.value = it },
            startValue = viewModel.desiredAnnualSalarySliderValues.value.start.toInt()
                .toString(),
            endValue = viewModel.desiredAnnualSalarySliderValues.value.endInclusive.toInt()
                .toString(),
            onStartValueChange = {
                val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                val startValue = viewModel.desiredAnnualSalarySliderValues.value.start
                val endValue = viewModel.desiredAnnualSalarySliderValues.value.endInclusive

                viewModel.desiredAnnualSalarySliderValues.value = when (inputValue) {
                    0f -> if (startValue.toInt() == 0) 0f else inputValue
                    in 0f..endValue -> if (startValue.toInt() == 0) it.replace(
                        "0",
                        ""
                    )
                        .toFloat() else inputValue
                    else -> endValue
                }..endValue
            },
            onEndValueChange = {
                val inputValue = it.toFloatOrNull() ?: 0f
                val startValue = viewModel.desiredAnnualSalarySliderValues.value.start
                val endValue = viewModel.desiredAnnualSalarySliderValues.value.endInclusive

                viewModel.desiredAnnualSalarySliderValues.value = when {
                    inputValue in startValue..9999f -> {
                        val updatedStartValue = when {
                            inputValue == 0f && endValue.toInt() == 0 -> 0f
                            inputValue != 0f && endValue.toInt() == 0 -> inputValue.toInt()
                                .toString().replace("0", "").toFloat()
                            else -> inputValue
                        }
                        startValue..updatedStartValue
                    }
                    inputValue > endValue -> startValue..9999f
                    else -> startValue..startValue
                }
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}