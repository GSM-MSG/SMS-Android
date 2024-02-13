package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClassSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedClass: String,
    classList: List<String>,
    onSelectedClassChange: (value: String) -> Unit
){
    SelectorBottomSheet(
        list = classList,
        bottomSheetState = bottomSheetState,
        selected = selectedClass,
        itemChange = onSelectedClassChange
    )
}