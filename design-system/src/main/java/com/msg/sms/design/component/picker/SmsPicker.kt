package com.msg.sms.design.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsPicker(itemList: List<String>, selectedItem: (value: String) -> Unit) {
    val listState = rememberLazyListState()
    val boxPosition = remember {
        mutableStateOf(0f)
    }

    SMSTheme { colors, typography ->
        Box {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(32.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(8.dp))
                    .onGloballyPositioned {
                        boxPosition.value = it.positionInWindow().y
                    }
            )
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .height(163.dp)
                    .width(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
                itemsIndexed(itemList) { _, item ->
                    val isSelected = remember {
                        mutableStateOf(false)
                    }

                    if (isSelected.value) selectedItem(item)

                    Text(
                        text = item,
                        style = if (isSelected.value) typography.title1 else typography.title2,
                        color = if (isSelected.value) colors.BLACK else colors.N30,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 3.dp)
                            .onGloballyPositioned {
                                isSelected.value =
                                    it.positionInWindow().y in boxPosition.value..boxPosition.value + 70
                            }
                    )
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
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        SmsPicker(
            itemList = listOf(
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
                "2018"
            )
        ) {}
    }
}