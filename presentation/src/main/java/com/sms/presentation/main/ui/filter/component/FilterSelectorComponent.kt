package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterSelectorComponent(
    title: String,
    itemList: List<Pair<String, Boolean>>,
    selectedItem: (List<Pair<String, Boolean>>) -> Unit
) {
    val list = itemList.toMutableStateList()
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .heightIn(max = 300.dp)
        ) {
            Text(
                text = title,
                style = typography.body1,
                color = colors.BLACK,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                userScrollEnabled = false
            ) {
                items(itemList.size) { index ->
                    Column {
                        FilterItem(
                            item = itemList[index]
                        ) { checked, text ->
                            list[index] = Pair(text, checked)
                            selectedItem(list)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}