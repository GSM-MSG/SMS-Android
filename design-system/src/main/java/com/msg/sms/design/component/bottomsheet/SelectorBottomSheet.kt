package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import com.msg.sms.design.component.selector.MajorSelector
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectorBottomSheet(
    list: List<String>,
    bottomSheetState: ModalBottomSheetState,
    selected: String,
    lastItem: @Composable (() -> Unit) = {},
    itemChange: (String) -> Unit,
    ) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(list.size) {
            MajorSelector(major = list[it], selected = selected == list[it]) {
                itemChange(list[it])
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
        item {
            lastItem()
        }
    }
}