package com.sms.presentation.main.ui.fill_out_information.component.bottomsheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MilitarySelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedMilitaryService: String,
    militaryServiceList: List<String>,
    onSelectedMilitaryServiceChange: (value: String) -> Unit,
) {
    SelectorBottomSheet(
        list = militaryServiceList,
        bottomSheetState = bottomSheetState,
        selected = selectedMilitaryService,
        itemChange = onSelectedMilitaryServiceChange
    )
}