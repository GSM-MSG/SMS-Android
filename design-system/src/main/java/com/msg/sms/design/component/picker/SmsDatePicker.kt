package com.msg.sms.design.component.picker

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsDatePicker(
    modifier: Modifier = Modifier,
    onYearValueChange: (String) -> Unit,
    onMonthValueChange: (String) -> Unit
) {
    SMSTheme { colors, _ ->
        Box(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.N10)
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                SmsPicker(
                    modifier = Modifier.weight(1f),
                    itemRange = 2015..2030,
                    onSelectedItemChange = onYearValueChange
                )
                SmsPicker(
                    modifier = Modifier.weight(1f),
                    itemRange = 1..12,
                    onSelectedItemChange = onMonthValueChange
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                colors.WHITE,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                colors.WHITE
                            )
                        )
                    )
            )
        }
    }
}

@Preview
@Composable
fun SmsDatePickerPre() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SmsDatePicker(
            onMonthValueChange = { Log.d("dd", it) },
            onYearValueChange = { Log.d("dd", it) }
        )
    }
}