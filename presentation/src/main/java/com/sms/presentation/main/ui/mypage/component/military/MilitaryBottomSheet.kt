package com.sms.presentation.main.ui.mypage.component.military

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.msg.sms.design.component.selector.MajorSelector
import com.sms.presentation.main.ui.mypage.state.MilitaryService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MilitaryBottomSheet(
    list: List<MilitaryService>,
    selected: String,
    bottomSheetState: ModalBottomSheetState,
    itemChange: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(list.size) {
            MajorSelector(major = list[it].text, selected = selected == list[it].name) {
                itemChange(list[it].name)
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    }
}