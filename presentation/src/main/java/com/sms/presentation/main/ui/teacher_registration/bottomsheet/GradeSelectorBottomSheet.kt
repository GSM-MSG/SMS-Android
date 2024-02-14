package com.sms.presentation.main.ui.teacher_registration.bottomsheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.teacher_registration.state.Grade
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GradeSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedGrade: String,
    gradeList: List<Grade>,
    onSelectedGradeChange: (value: String) -> Unit
){
    val coroutineScope = rememberCoroutineScope()

    LazyColumn{
        items(gradeList.size){
            MajorSelector(major = gradeList[it].text, selected = selectedGrade == gradeList[it].name) {
                onSelectedGradeChange(gradeList[it].name)
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    }
}