package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GradeSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedGrade: String,
    gradeList: List<String>,
    onSelectedGradeChange: (value: String) -> Unit
){
    SelectorBottomSheet(
        list = gradeList,
        bottomSheetState = bottomSheetState,
        selected = selectedGrade,
        itemChange = onSelectedGradeChange
    )
}