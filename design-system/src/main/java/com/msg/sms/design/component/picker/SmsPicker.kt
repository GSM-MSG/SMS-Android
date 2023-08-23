package com.msg.sms.design.component.picker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsPicker(
    modifier: Modifier = Modifier,
    itemList: List<String> = emptyList(),
    itemRange: Iterable<Int> = emptyList(),
    selectedItem: (value: String) -> Unit
) {
    val listState = rememberLazyListState()
    val boxPosition = remember {
        mutableStateOf(0f)
    }

    SMSTheme { colors, typography ->
        Box {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .onGloballyPositioned {
                        boxPosition.value = it.positionInWindow().y
                    }
            )
            LazyColumn(
                modifier = modifier,
                state = listState,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
                itemsIndexed(itemList.ifEmpty { itemRange.map { it.toString() } }) { idx, item ->
                    val isSelected = remember {
                        mutableStateOf(false)
                    }

                    if (isSelected.value) {
                        selectedItem(item)
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned {
                                isSelected.value =
                                    boxPosition.value in it.positionInWindow().y..it.positionInWindow().y + it.size.height
                            }
                    ) {
                        Text(
                            text = item,
                            style = if (isSelected.value) typography.title1 else typography.title2,
                            color = if (isSelected.value) colors.BLACK else colors.N30,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun SmsPickerPre() {
    SmsPicker(
        itemList = listOf(
            "2012",
            "2013",
            "2014",
            "2015",
            "2016",
            "2017",
            "2018"
        ),
    ) {}
}