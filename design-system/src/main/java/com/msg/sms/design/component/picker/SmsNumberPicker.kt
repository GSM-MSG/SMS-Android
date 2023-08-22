package com.msg.sms.design.component.picker

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsNumberPicker(itemList: List<String>) {
    val listState = rememberLazyListState()

    SMSTheme { colors, typography ->
        Box {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(32.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.N10)
                    .onGloballyPositioned {

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
                itemsIndexed(itemList) { idx, item ->
                    Text(
                        text = item,
                        style = typography.title1,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 3.dp)
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