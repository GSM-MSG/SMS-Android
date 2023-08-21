package com.msg.sms.design.component.picker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsNumberPicker(itemList: List<String>) {
    val listState = rememberLazyListState()
    LaunchedEffect("ListState") {
    }

    SMSTheme { colors, typography ->
        Box(modifier = Modifier.wrapContentSize()) {
            LazyColumn(
                modifier = Modifier
                    .width(150.dp)
                    .height(163.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                state = listState
            ) {
                item { Spacer(modifier = Modifier.height(32.dp)) }
                itemsIndexed(itemList) { index, item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 5.dp),
                        style = typography.title1,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold
                    )
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }
}

@Preview
@Composable
fun SmsNumberPickerPre() {
    SmsNumberPicker(
        itemList = listOf(
            "2012",
            "2013",
            "2014",
            "2015",
            "2016",
            "2017",
            "2018",
            "2019",
            "2020",
            "2021",
            "2022",
            "2023",
            "2024",
            "2025",
            "2026",
            "2027",
            "2028",
            "2029"
        )
    )
}