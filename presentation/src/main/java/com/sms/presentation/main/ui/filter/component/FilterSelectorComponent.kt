package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.util.AddBody1TitleText

@Composable
fun <T> FilterSelectorComponent(
    title: String,
    itemList: List<T>,
    selectedList: List<T>,
    onItemSelected: (checked: Boolean, value: T) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .heightIn(max = 300.dp)
    ) {
        AddBody1TitleText(titleText = title) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                userScrollEnabled = false
            ) {
                items(itemList.size) { index ->
                    Column {
                        FilterItem(
                            item = itemList[index],
                            checked = selectedList.contains(itemList[index])
                        ) { checked, text ->
                            onItemSelected(checked, text)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterSelectorComponentPre() {
    FilterSelectorComponent(
        title = "text",
        itemList = listOf("aa", "bb", "cc"),
        selectedList = listOf("aa"),
        onItemSelected = { checked: Boolean, value: String -> })
}