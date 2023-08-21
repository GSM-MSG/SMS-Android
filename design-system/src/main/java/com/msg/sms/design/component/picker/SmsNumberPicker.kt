package com.msg.sms.design.component.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsNumberPicker(itemList: List<String>) {
    val listState = rememberLazyListState()


    SMSTheme { colors, typography ->
        LazyColumn(state = listState) {
            itemsIndexed(itemList) { idx, item ->
                Text(
                    text = item,
                    style = typography.title1,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun SmsNumberPickerPre() {
    Column {
        Spacer(modifier = Modifier.height(30.dp))
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
}