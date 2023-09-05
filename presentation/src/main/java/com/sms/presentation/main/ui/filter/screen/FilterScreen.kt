package com.sms.presentation.main.ui.filter.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsBoxButton
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSearchTechStackComponent
import com.sms.presentation.main.ui.filter.component.FilterSelectionControlsGroup
import com.sms.presentation.main.ui.filter.component.FilterSelectorGroup
import com.sms.presentation.main.ui.filter.component.FilterSliderGroup
import com.sms.presentation.main.ui.filter.data.FilterClass
import com.sms.presentation.main.ui.filter.data.FilterDepartment
import com.sms.presentation.main.ui.filter.data.FilterGrade
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment

@Composable
fun FilterScreen(
    role: String,
    detailStacks: List<String>,
    onBackPressed: () -> Unit,
    onChangeToMainPage: () -> Unit,
    onChangeToSearchPage: () -> Unit,
    onRightButtonClick: () -> Unit,
    onLeftButtonClick: () -> Unit,
    onFilteringTechStackValueChanged: (techStack: List<String>) -> Unit,
    //Selector
    gradeList: List<FilterGrade>,
    classList: List<FilterClass>,
    departmentList: List<FilterDepartment>,
    majorList: List<String>,
    typeOfEmploymentList: List<FilterTypeOfEmployment>,
    selectedGradeList: List<FilterGrade>,
    selectedClassList: List<FilterClass>,
    selectedDepartmentList: List<FilterDepartment>,
    selectedMajorList: List<String>,
    selectedTypeOfEmploymentList: List<FilterTypeOfEmployment>,
    onGradeListValueChanged: (gradeList: List<FilterGrade>) -> Unit,
    onClassListValueChanged: (classList: List<FilterClass>) -> Unit,
    onDepartmentListValueChanged: (departmentList: List<FilterDepartment>) -> Unit,
    onMajorListValueChanged: (mojorList: List<String>) -> Unit,
    onTypeOfEmploymentListValueChanged: (typeOfEmploymentList: List<FilterTypeOfEmployment>) -> Unit,
    //Slider
    selectedGsmScoreSliderValue: ClosedFloatingPointRange<Float>,
    selectedDesiredAnnualSalarySliderValue: ClosedFloatingPointRange<Float>,
    onGsmScoreSliderValueChanged: (value: ClosedFloatingPointRange<Float>) -> Unit,
    onDesiredAnnualSalarySliderValueChanged: (value: ClosedFloatingPointRange<Float>) -> Unit,
    //SelectionCtroll
    selectedSchoolNumberAscendingValue: Boolean,
    selectedGsmScoreAscendingValue: Boolean,
    selectedDesiredAnnualSalaryAscendingValue: Boolean,
    onSchoolNumberAscendingValueChanged: (value: Boolean) -> Unit,
    onGsmScoreAscendingValueChanged: (value: Boolean) -> Unit,
    onDesiredAnnualSalaryAscendingValueChanged: (value: Boolean) -> Unit
) {
    val scrollState = rememberScrollState()
    val selectorResetButtonClick = remember {
        mutableStateOf(false)
    }
    val sliderResetButtonClick = remember {
        mutableStateOf(false)
    }
    val selectionControlResetButtonClick = remember {
        mutableStateOf(false)
    }

    BackHandler {
        onBackPressed()
    }

    SMSTheme { colors, typography ->
        Scaffold(
            bottomBar = {
                SmsBoxButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "확인",
                    enabled = true,
                    onClick = onChangeToMainPage
                )
            }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(colors.WHITE)
                    .verticalScroll(scrollState)
            ) {
                TopBarComponent(
                    text = "필터",
                    leftIcon = {
                        Text(
                            text = "초기화",
                            style = typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = colors.BLACK
                        )
                    },
                    rightIcon = { DeleteButtonIcon() },
                    onClickLeftButton = {
                        selectorResetButtonClick.value = true
                        sliderResetButtonClick.value = true
                        selectionControlResetButtonClick.value = true
                        onLeftButtonClick()
                    },
                    onClickRightButton = onRightButtonClick
                )
                Divider(thickness = 16.dp, color = colors.N10)
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.fillMaxSize()) {
                    FilterSelectorGroup(
                        role = role,
                        resetButtonClick = selectorResetButtonClick.value,
                        onResetButtonClickValueChanged = {
                            selectorResetButtonClick.value = it
                        },
                        gradeList = gradeList,
                        classList = classList,
                        departmentList = departmentList,
                        majorList = majorList,
                        typeOfEmploymentList = typeOfEmploymentList,
                        selectedGradeList = selectedGradeList,
                        selectedClassList = selectedClassList,
                        selectedDepartmentList = selectedDepartmentList,
                        selectedMajorList = selectedMajorList,
                        selectedTypeOfEmploymentList = selectedTypeOfEmploymentList,
                        onGradeListValueChanged = onGradeListValueChanged,
                        onClassListValueChanged = onClassListValueChanged,
                        onDepartmentListValueChanged = onDepartmentListValueChanged,
                        onMajorListValueChanged = onMajorListValueChanged,
                        onTypeOfEmploymentListValueChanged = onTypeOfEmploymentListValueChanged
                    )
                    FilterSliderGroup(
                        role = role,
                        resetButtonClick = sliderResetButtonClick.value,
                        onResetButtonClickValueChanged = {
                            sliderResetButtonClick.value = it
                        },
                        selectedGsmScoreSliderValue = selectedGsmScoreSliderValue,
                        selectedDesiredAnnualSalarySliderValue = selectedDesiredAnnualSalarySliderValue,
                        onGsmScoreSliderValueChanged = onGsmScoreSliderValueChanged,
                        onDesiredAnnualSalarySliderValueChanged = onDesiredAnnualSalarySliderValueChanged
                    )
                    FilterSelectionControlsGroup(
                        role = role,
                        resetButtonClick = selectionControlResetButtonClick.value,
                        onResetButtonClickValueChanged = {
                            selectionControlResetButtonClick.value = it
                        },
                        selectedSchoolNumberAscendingValue = selectedSchoolNumberAscendingValue,
                        selectedGsmScoreAscendingValue = selectedGsmScoreAscendingValue,
                        selectedDesiredAnnualSalaryAscendingValue = selectedDesiredAnnualSalaryAscendingValue,
                        onSchoolNumberAscendingValueChanged = onSchoolNumberAscendingValueChanged,
                        onGsmScoreAscendingValueChanged = onGsmScoreAscendingValueChanged,
                        onDesiredAnnualSalaryAscendingValueChanged = onDesiredAnnualSalaryAscendingValueChanged
                    )
                    FilterSearchTechStackComponent(
                        techStack = detailStacks,
                        onClick = onChangeToSearchPage,
                        onFilteringTechStackValueChanged = onFilteringTechStackValueChanged
                    )
                    Spacer(modifier = Modifier.height(it.calculateBottomPadding() + 64.dp))
                }
            }
        }
    }
}