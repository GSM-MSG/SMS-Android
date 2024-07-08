package com.sms.presentation.main.ui.teacher_registration.bottomsheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.selector.MajorSelector
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PositionSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedPosition: String,
    positionList: List<String>,
    onSelectedPositionChange: (value: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(positionList.size){
            MajorSelector(major = positionList[it], selected = selectedPosition == positionList[it]) {
                onSelectedPositionChange(positionList[it])
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    }
}