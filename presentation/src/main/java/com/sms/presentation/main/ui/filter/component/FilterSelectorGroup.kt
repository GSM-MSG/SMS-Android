package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
    onGradeListValueChanged: (gradeList: List<String>) -> Unit,
    onClassListValueChanged: (classList: List<String>) -> Unit,
    onDepartmentListValueChanged: (departmentList: List<String>) -> Unit,
    onMajorListValueChanged: (mojorList: List<String>) -> Unit,
    onTypeOfEmploymentListValueChanged: (typeOfEmploymentList: List<String>) -> Unit,
) {
    val filterGradeList = remember {
        mutableStateListOf(*selectedGradeList.toTypedArray())
    }
    val filterClassList = remember {
        mutableStateListOf(*selectedClassList.toTypedArray())
    }
    val filterDepartmentList = remember {
        mutableStateListOf(*selectedDepartmentList.toTypedArray())
    }
    val filterMajorList = remember {
        mutableStateListOf(*selectedMajorList.toTypedArray())
    }
    val filterTypeOfEmploymentList = remember {
        mutableStateListOf(*selectedTypeOfEmploymentList.toTypedArray())
    }

    onGradeListValueChanged(filterGradeList)
    onClassListValueChanged(filterClassList)
    onDepartmentListValueChanged(filterDepartmentList)
    onMajorListValueChanged(filterMajorList)
    onTypeOfEmploymentListValueChanged(filterTypeOfEmploymentList)

    if (role != "") {
        FilterSelectorComponent(
            title = "학년",
            itemList = gradeList,
            selectedList = filterGradeList,
            onItemSelected = { checked, value ->
                if (!checked) filterGradeList.add(value)
                else filterGradeList.remove(value)
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectorComponent(
            title = "반",
            itemList = classList,
            selectedList = filterClassList,
            onItemSelected = { checked, value ->
                if (!checked) filterClassList.add(value)
                else filterClassList.remove(value)
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilterSelectorComponent(
            title = "학과",
            itemList = departmentList,
            selectedList = filterDepartmentList,
            onItemSelected = { checked, value ->
                if (!checked) filterDepartmentList.add(value)
                else filterDepartmentList.remove(value)
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (majorList.isNotEmpty()) {
        FilterSelectorComponent(
            title = "분야",
            itemList = majorList,
            selectedList = filterMajorList,
            onItemSelected = { checked, value ->
                if (!checked) filterMajorList.add(value)
                else filterMajorList.remove(value)
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (role == "ROLE_TEACHER") {
        FilterSelectorComponent(
            title = "희망 고용 형태",
            itemList = typeOfEmploymentList,
            selectedList = filterTypeOfEmploymentList,
            onItemSelected = { checked, value ->
                if (!checked) filterTypeOfEmploymentList.add(value)
                else filterTypeOfEmploymentList.remove(value)
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}