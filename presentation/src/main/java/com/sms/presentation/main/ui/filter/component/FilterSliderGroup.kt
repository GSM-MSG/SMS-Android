package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSliderGroup(
    role: String,
    selectedGsmScoreSliderValue: ClosedFloatingPointRange<Float>,
    selectedDesiredAnnualSalarySliderValue: ClosedFloatingPointRange<Float>,
    onGsmScoreSliderValueChanged: (value: ClosedFloatingPointRange<Float>) -> Unit,
    onDesiredAnnualSalarySliderValueChanged: (value: ClosedFloatingPointRange<Float>) -> Unit
) {
    val gsmScoreSliderValue = remember {
        mutableStateOf(selectedGsmScoreSliderValue)
    }
    val desiredAnnualSalarySliderValue = remember {
        mutableStateOf(selectedDesiredAnnualSalarySliderValue)
    }

    onGsmScoreSliderValueChanged(gsmScoreSliderValue.value)
    onDesiredAnnualSalarySliderValueChanged(desiredAnnualSalarySliderValue.value)

    if (role == "ROLE_TEACHER") {
        FilterSliderComponent(
            title = "인증제 점수",
            sliderValue = gsmScoreSliderValue.value,
            valueRange = 0f..990f,
            onSliderValueChange = { gsmScoreSliderValue.value = it },
            startValue = gsmScoreSliderValue.value.start.toInt().toString(),
            endValue = gsmScoreSliderValue.value.endInclusive.toInt().toString(),
            onStartValueChange = {
                val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                val startValue = gsmScoreSliderValue.value.start
                val endValue = gsmScoreSliderValue.value.endInclusive

                gsmScoreSliderValue.value = when (inputValue) {
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
                val startValue = gsmScoreSliderValue.value.start
                val endValue = gsmScoreSliderValue.value.endInclusive

                gsmScoreSliderValue.value = when {
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
            sliderValue = desiredAnnualSalarySliderValue.value,
            valueRange = 0f..9999f,
            onSliderValueChange = { desiredAnnualSalarySliderValue.value = it },
            startValue = desiredAnnualSalarySliderValue.value.start.toInt().toString(),
            endValue = desiredAnnualSalarySliderValue.value.endInclusive.toInt().toString(),
            onStartValueChange = {
                val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                val startValue = desiredAnnualSalarySliderValue.value.start
                val endValue = desiredAnnualSalarySliderValue.value.endInclusive

                desiredAnnualSalarySliderValue.value = when (inputValue) {
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
                val startValue = desiredAnnualSalarySliderValue.value.start
                val endValue = desiredAnnualSalarySliderValue.value.endInclusive

                desiredAnnualSalarySliderValue.value = when {
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