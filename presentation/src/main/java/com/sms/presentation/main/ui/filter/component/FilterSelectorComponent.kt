package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun <T> FilterSelectorComponent(
    title: String,
    itemList: List<T>,
    selectedList: List<T>,
    onItemSelected: (checked: Boolean, value: T) -> Unit
) {

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