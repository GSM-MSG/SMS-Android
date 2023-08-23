package com.sms.presentation.main.ui.fill_out_information.component.bottomsheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.selector.MajorSelector
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MajorSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedMajor: String,
    majorList: List<String>,
    onSelectedMajhorChange: (value: String) -> Unit,
) {
    val scope = rememberCoroutineScope()

    SelectorBottomSheet(
        list = majorList,
        bottomSheetState = bottomSheetState,
        selected = selectedMajor,
        itemChange = onSelectedMajhorChange,
        lastItem = {
            MajorSelector(
                major = "직접입력",
                selected = selectedMajor == "직접입력"
            ) {
                onSelectedMajhorChange("직접입력")
                scope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    )
}