package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.viewmodel.StudentListViewModel

@Composable
fun FilterSelectorGroup(
    role: String,
    viewModel: StudentListViewModel
) {
    if (role != "") {
        FilterSelectorComponent(
            title = "학년",
            itemList = viewModel.gradeList,
            selectedList = viewModel.selectedGradeList.map { it.toString() }
        ) { checked, text ->
            if (!checked) viewModel.selectedGradeList.add(text.toInt())
            else viewModel.selectedGradeList.remove(text.toInt())
        }
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectorComponent(
            title = "반",
            itemList = viewModel.classList,
            selectedList = viewModel.selectedClassList.map { it.toString() }
        ) { checked, text ->
            if (!checked) viewModel.selectedClassList.add(text.toInt())
            else viewModel.selectedClassList.remove(text.toInt())
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
    if (viewModel.majorList.isNotEmpty()) {
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
        FilterSelectorComponent(
            title = "희망 고용 형태",
            itemList = viewModel.typeOfEmploymentList,
            selectedList = viewModel.selectedTypeOfEmploymentList
        ) { checked, text ->
            if (!checked) viewModel.selectedTypeOfEmploymentList.add(text)
            else viewModel.selectedTypeOfEmploymentList.remove(text)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}