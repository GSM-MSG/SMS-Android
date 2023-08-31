package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSelectorGroup(
    role: String,
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
    onGradeListValueChanged: (checked: Boolean, grade: String) -> Unit,
    onClassListValueChanged: (checked: Boolean, `class`: String) -> Unit,
    onDepartmentListValueChanged: (checked: Boolean, department: String) -> Unit,
    onMajorListValueChanged: (checked: Boolean, major: String) -> Unit,
    onTypeOfEmploymentListValueChanged: (checked: Boolean, typeOfEmployment: String) -> Unit,
) {
    if (role != "") {
        FilterSelectorComponent(
            title = "학년",
            itemList = gradeList,
            selectedList = selectedGradeList,
            onItemSelected = onGradeListValueChanged
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectorComponent(
            title = "반",
            itemList = classList,
            selectedList = selectedClassList,
            onItemSelected = onClassListValueChanged
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectorComponent(
            title = "학과",
            itemList = departmentList,
            selectedList = selectedDepartmentList,
            onItemSelected = onDepartmentListValueChanged
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (majorList.isNotEmpty()) {
        FilterSelectorComponent(
            title = "분야",
            itemList = majorList,
            selectedList = selectedMajorList,
            onItemSelected = onMajorListValueChanged
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (role == "ROLE_TEACHER") {
        FilterSelectorComponent(
            title = "희망 고용 형태",
            itemList = typeOfEmploymentList,
            selectedList = selectedTypeOfEmploymentList,
            onItemSelected = onTypeOfEmploymentListValueChanged
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}