package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PositionSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedPosition: String,
    positionList: List<String>,
    onSelectedPositionChange: (value: String) -> Unit
) {
    SelectorBottomSheet(
        list = positionList,
        bottomSheetState = bottomSheetState,
        selected = selectedPosition,
        itemChange = onSelectedPositionChange
    )
}