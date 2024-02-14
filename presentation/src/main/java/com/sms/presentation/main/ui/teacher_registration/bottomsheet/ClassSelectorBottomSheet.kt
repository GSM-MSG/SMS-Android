package com.sms.presentation.main.ui.teacher_registration.bottomsheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.teacher_registration.state.Class
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClassSelectorBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    selectedClass: String,
    classList: List<Class>,
    onSelectedClassChange: (value: String) -> Unit
){
    val coroutineScope = rememberCoroutineScope()

    LazyColumn{
        items(classList.size){
            MajorSelector(major = classList[it].text, selected = selectedClass == classList[it].name) {
                onSelectedClassChange(classList[it].name)
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    }
}