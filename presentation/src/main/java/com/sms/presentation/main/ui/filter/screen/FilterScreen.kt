package com.sms.presentation.main.ui.filter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSelectorComponent
import com.sms.presentation.main.ui.filter.component.FilterSliderComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
    val gsmScoreSliderValues = remember {
        mutableStateOf(0f..990f)
    }

    val desiredAnnualSalarySliderValues = remember {
        mutableStateOf(0f..9999f)
    }

    SMSTheme { colors, typography ->
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "필터", leftIcon = {
                Text(
                    text = "초기화",
                    style = typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = colors.BLACK
                )
            }, rightIcon = { DeleteButtonIcon() }, onClickLeftButton = {
                viewModel.resetFilter()
            }, onClickRightButton = {
                navController.popBackStack()
            })
            Divider(thickness = 16.dp, color = colors.N10)
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (role != "") {
                    item {
                        FilterSelectorComponent(
                            title = "학년",
                            itemList = viewModel.gradeList,
                            selectedList = viewModel.selectedGradeList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedGradeList.add(text)
                            else viewModel.selectedGradeList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    item {
                        FilterSelectorComponent(
                            title = "반",
                            itemList = viewModel.classList,
                            selectedList = viewModel.selectedClassList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedClassList.add(text)
                            else viewModel.selectedClassList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    item {
                        FilterSelectorComponent(
                            title = "학과",
                            itemList = viewModel.departmentList,
                            selectedList = viewModel.selectedDepartmentList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedDepartmentList.add(text)
                            else viewModel.selectedDepartmentList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
                item {
                    FilterSelectorComponent(
                        title = "분야",
                        itemList = viewModel.majorList,
                        selectedList = viewModel.selectedMajorList
                    ) { checked, text ->
                        if (!checked) viewModel.selectedMajorList.add(text)
                        else viewModel.selectedMajorList.remove(text)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                if (role == "ROLE_TEACHER") {
                    item {
                        FilterSelectorComponent(
                            title = "희망 고용 형태",
                            itemList = viewModel.typeOfEmploymentList,
                            selectedList = viewModel.selectedTypeOfEmploymentList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedTypeOfEmploymentList.add(text)
                            else viewModel.selectedTypeOfEmploymentList.remove(text)
                        }
                    }
                }
                item {
                    FilterSliderComponent(
                        title = "인증제 점수",
                        sliderValue = gsmScoreSliderValues.value,
                        valueRange = 0f..990f,
                        onSliderValueChange = { gsmScoreSliderValues.value = it },
                        startValue = gsmScoreSliderValues.value.start.toInt().toString(),
                        endValue = gsmScoreSliderValues.value.endInclusive.toInt().toString(),
                        onStartValueChange = {
                            if (it.isNotBlank()) {
                                if (it.toFloat() in 0f..gsmScoreSliderValues.value.endInclusive) {
                                    gsmScoreSliderValues.value = when {
                                        it == "00" -> 0f
                                        gsmScoreSliderValues.value.start.toInt() == 0 -> it.replace(
                                            "0",
                                            ""
                                        ).toFloat()
                                        else -> it.toFloat()
                                    }..gsmScoreSliderValues.value.endInclusive
                                } else {
                                    gsmScoreSliderValues.value =
                                        gsmScoreSliderValues.value.endInclusive..gsmScoreSliderValues.value.endInclusive
                                }
                            } else {
                                gsmScoreSliderValues.value =
                                    0f..gsmScoreSliderValues.value.endInclusive
                            }
                        },
                        onEndValueChange = {
                            if (it.isNotBlank()) {
                                if (it.toFloat() in gsmScoreSliderValues.value.start..990f) {
                                    gsmScoreSliderValues.value =
                                        gsmScoreSliderValues.value.start..when {
                                            it == "00" -> 0f
                                            gsmScoreSliderValues.value.endInclusive.toInt() == 0 -> it.replace(
                                                "0",
                                                ""
                                            ).toFloat()
                                            else -> it.toFloat()
                                        }
                                } else {
                                    gsmScoreSliderValues.value =
                                        gsmScoreSliderValues.value.start..gsmScoreSliderValues.value.start
                                }
                            } else {
                                gsmScoreSliderValues.value =
                                    0f..0f
                            }
                        }
                    )
                }
                item {
                    FilterSliderComponent(
                        title = "희망연봉",
                        sliderValue = desiredAnnualSalarySliderValues.value,
                        valueRange = 0f..9999f,
                        onSliderValueChange = { desiredAnnualSalarySliderValues.value = it },
                        startValue = "${desiredAnnualSalarySliderValues.value.start.toInt()} 만원",
                        endValue = "${desiredAnnualSalarySliderValues.value.endInclusive.toInt()} 만원",
                        onStartValueChange = {},
                        onEndValueChange = {}
                    )
                }
            }
        }
    }
}