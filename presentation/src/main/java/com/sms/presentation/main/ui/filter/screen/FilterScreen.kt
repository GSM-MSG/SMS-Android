package com.sms.presentation.main.ui.filter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.icon.SearchIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSelectionControls
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
    val isSchoolNumberAscendingOrder = remember {
        mutableStateOf(true)
    }
    val isGsmScoreAscendingOrder = remember {
        mutableStateOf(true)
    }
    val isDesiredAnnualSalaryAscendingOrder = remember {
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()

    SMSTheme { colors, typography ->
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.WHITE)
                .verticalScroll(scrollState)
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
            Column(modifier = Modifier.fillMaxSize()) {
                if (role != "") {
                    FilterSelectorComponent(
                        title = "학년",
                        itemList = viewModel.gradeList,
                        selectedList = viewModel.selectedGradeList
                    ) { checked, text ->
                        if (!checked) viewModel.selectedGradeList.add(text)
                        else viewModel.selectedGradeList.remove(text)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    FilterSelectorComponent(
                        title = "반",
                        itemList = viewModel.classList,
                        selectedList = viewModel.selectedClassList
                    ) { checked, text ->
                        if (!checked) viewModel.selectedClassList.add(text)
                        else viewModel.selectedClassList.remove(text)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
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
                FilterSelectorComponent(
                    title = "분야",
                    itemList = viewModel.majorList,
                    selectedList = viewModel.selectedMajorList
                ) { checked, text ->
                    if (!checked) viewModel.selectedMajorList.add(text)
                    else viewModel.selectedMajorList.remove(text)
                }
                Spacer(modifier = Modifier.height(40.dp))
                if (role == "ROLE_TEACHER") {
                    FilterSelectorComponent(
                        title = "희망 고용 형태",
                        itemList = viewModel.typeOfEmploymentList,
                        selectedList = viewModel.selectedTypeOfEmploymentList
                    ) { checked, text ->
                        if (!checked) viewModel.selectedTypeOfEmploymentList.add(text)
                        else viewModel.selectedTypeOfEmploymentList.remove(text)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    FilterSliderComponent(
                        title = "인증제 점수",
                        sliderValue = gsmScoreSliderValues.value,
                        valueRange = 0f..990f,
                        onSliderValueChange = { gsmScoreSliderValues.value = it },
                        startValue = gsmScoreSliderValues.value.start.toInt().toString(),
                        endValue = gsmScoreSliderValues.value.endInclusive.toInt().toString(),
                        onStartValueChange = {
                            val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                            val startValue = gsmScoreSliderValues.value.start
                            val endValue = gsmScoreSliderValues.value.endInclusive

                            gsmScoreSliderValues.value = when (inputValue) {
                                0f -> if (startValue.toInt() == 0) 0f else inputValue
                                in 0f..endValue -> if (startValue.toInt() == 0) it.replace("0", "")
                                    .toFloat() else inputValue
                                else -> endValue
                            }..endValue
                        },
                        onEndValueChange = {
                            val inputValue = it.toFloatOrNull() ?: 0f
                            val startValue = gsmScoreSliderValues.value.start
                            val endValue = gsmScoreSliderValues.value.endInclusive

                            gsmScoreSliderValues.value = when {
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
                        sliderValue = desiredAnnualSalarySliderValues.value,
                        valueRange = 0f..9999f,
                        onSliderValueChange = { desiredAnnualSalarySliderValues.value = it },
                        startValue = desiredAnnualSalarySliderValues.value.start.toInt().toString(),
                        endValue = desiredAnnualSalarySliderValues.value.endInclusive.toInt()
                            .toString(),
                        onStartValueChange = {
                            val inputValue = if (it.isNotBlank()) it.toFloat() else 0f
                            val startValue = desiredAnnualSalarySliderValues.value.start
                            val endValue = desiredAnnualSalarySliderValues.value.endInclusive

                            desiredAnnualSalarySliderValues.value = when (inputValue) {
                                0f -> if (startValue.toInt() == 0) 0f else inputValue
                                in 0f..endValue -> if (startValue.toInt() == 0) it.replace("0", "")
                                    .toFloat() else inputValue
                                else -> endValue
                            }..endValue
                        },
                        onEndValueChange = {
                            val inputValue = it.toFloatOrNull() ?: 0f
                            val startValue = desiredAnnualSalarySliderValues.value.start
                            val endValue = desiredAnnualSalarySliderValues.value.endInclusive

                            desiredAnnualSalarySliderValues.value = when {
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
                if (role != "") {
                    FilterSelectionControls(
                        title = "학번",
                        firstSelectionName = "오름차순",
                        secondSelectionName = "내림차순",
                        selectionValue = isSchoolNumberAscendingOrder.value,
                        onSelectionClick = {
                            isSchoolNumberAscendingOrder.value = it
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
                if (role == "ROLE_TEACHER") {
                    FilterSelectionControls(
                        title = "인증제 점수",
                        firstSelectionName = "오름차순",
                        secondSelectionName = "내림차순",
                        selectionValue = isGsmScoreAscendingOrder.value,
                        onSelectionClick = {
                            isGsmScoreAscendingOrder.value = it
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    FilterSelectionControls(
                        title = "희망 연봉",
                        firstSelectionName = "오름차순",
                        secondSelectionName = "내림차순",
                        selectionValue = isDesiredAnnualSalaryAscendingOrder.value,
                        onSelectionClick = {
                            isDesiredAnnualSalaryAscendingOrder.value = it
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "세부스택",
                        style = typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = colors.BLACK
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SmsCustomTextField(
                        placeHolder = "찾고 싶은 세부스택 입력",
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(FocusRequester())
                            .onFocusChanged {
                                if (it.isFocused) {

                                }
                            },
                        setChangeText = "",
                        readOnly = true,
                        endIcon = null,
                        leadingIcon = { SearchIcon() },
                        onValueChange = {},
                        clickAction = {}
                    )
                }
            }
        }
    }
}