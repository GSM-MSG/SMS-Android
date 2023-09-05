package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.util.AddBody1Title
import com.sms.presentation.main.ui.filter.data.FilterClass
import com.sms.presentation.main.ui.filter.data.FilterDepartment
import com.sms.presentation.main.ui.filter.data.FilterGrade
import com.sms.presentation.main.ui.filter.data.FilterTypeOfEmployment

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
        AddBody1Title(titleText = title) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                userScrollEnabled = false
            ) {
                itemsIndexed(itemList) { _, item ->
                    Column {
                        val itemText = when (item) {
                            is FilterGrade -> item.value
                            is FilterClass -> item.value
                            is FilterTypeOfEmployment -> item.value
                            is FilterDepartment -> item.value
                            else -> item.toString()
                        }
                        val itemEnum = when (item) {
                            is FilterGrade -> item.enum
                            is FilterClass -> item.enum
                            is FilterTypeOfEmployment -> item.enum
                            is FilterDepartment -> item.enum
                            else -> item.toString()
                        }
                        FilterItem(
                            item = itemText,
                            checked = selectedList.contains(item)
                        ) { checked ->
                            onItemSelected(checked, item)
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
        onItemSelected = { _: Boolean, _: String -> })
}