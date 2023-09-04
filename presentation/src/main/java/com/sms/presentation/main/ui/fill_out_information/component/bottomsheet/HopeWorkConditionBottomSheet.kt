package com.sms.presentation.main.ui.fill_out_information.component.bottomsheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HopeWorkConditionBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedMajor: String,
    majorList: List<String>,
    onSelectedMajhorChange: (value: String) -> Unit,
) {
    SelectorBottomSheet(
        list = majorList,
        bottomSheetState = bottomSheetState,
        selected = selectedMajor,
        itemChange = onSelectedMajhorChange
    )
}