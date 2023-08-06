package com.msg.sms.design.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.theme.SMSTypography

@Composable
fun DatePicker() {
    val year = remember {
        mutableStateOf(0)
    }
    val month = remember {
        mutableStateOf(0)
    }

    SMSTheme { colors, _ ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
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
                    .align(Alignment.Center)
                    .height(163.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberPicker(
                    value = year.value,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    range = 2000..2030,
                    dividersColor = Color.Transparent,
                    textStyle = TextStyle(
                        fontFamily = SMSTypography.pretendard,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    onValueChange = { year.value = it }
                )
                NumberPicker(
                    value = month.value,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    range = 1..12,
                    dividersColor = Color.Transparent,
                    textStyle = TextStyle(
                        fontFamily = SMSTypography.pretendard,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    onValueChange = { month.value = it }
                )
            }
        }
    }
}

@Preview
@Composable
fun DatePickerPre() {
    DatePicker()
}