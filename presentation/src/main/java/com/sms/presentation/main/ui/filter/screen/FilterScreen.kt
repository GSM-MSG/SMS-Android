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
import com.sms.presentation.main.viewmodel.StudentListViewModel

@Composable
fun FilterScreen(
    viewModel: StudentListViewModel,
    role: String,
    detailStacks: List<String>,
    onBackPressed: () -> Unit,
    onChangeToMainPage: () -> Unit,
    onChangeToSearchPage: () -> Unit,
    onRightButtonClick: () -> Unit,
    onLeftButtonClick: () -> Unit,
    onFilteringTechStackValueChanged: (techStack: List<String>) -> Unit,
    //Selector
    gradeList: List<String>,
    classList: List<String>,
    departmentList: List<String>,
    majorList: List<String>,
    typeOfEmploymentList: List<String>,
    selectedGradeList: List<String>,
    selectedClassList: List<String>,
    selectedDepartmentList: List<String>,
    selectedMajorList: List<String>,
    selectedTypeOfEmploymentList: List<String>,
    onGradeListValueChanged: (gradeList: List<String>) -> Unit,
    onClassListValueChanged: (classList: List<String>) -> Unit,
    onDepartmentListValueChanged: (departmentList: List<String>) -> Unit,
    onMajorListValueChanged: (mojorList: List<String>) -> Unit,
    onTypeOfEmploymentListValueChanged: (typeOfEmploymentList: List<String>) -> Unit,
) {
    val scrollState = rememberScrollState()

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
                    onClickLeftButton = onLeftButtonClick,
                    onClickRightButton = onRightButtonClick
                )
                Divider(thickness = 16.dp, color = colors.N10)
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.fillMaxSize()) {
                    FilterSelectorGroup(
                        role = role,
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
                    FilterSliderGroup(role = role, viewModel = viewModel)
                    FilterSelectionControlsGroup(role = role, viewModel = viewModel)
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