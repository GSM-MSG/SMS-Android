package com.sms.presentation.main.ui.teacher_registration.bottomsheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.teacher_registration.state.Position
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PositionSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedPosition: String,
    positionList: List<Position>,
    onSelectedPositionChange: (value: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(positionList.size){
            MajorSelector(major = positionList[it].text, selected = selectedPosition == positionList[it].name) {
                onSelectedPositionChange(positionList[it].name)
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    }
}