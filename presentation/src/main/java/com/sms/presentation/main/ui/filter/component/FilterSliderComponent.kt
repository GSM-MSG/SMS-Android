package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.slider.SmsSlider
import com.msg.sms.design.component.textfield.FilterTextFiled
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterSliderComponent(
    title: String,
    isHopeSalary: Boolean = false,
    sliderValue: ClosedFloatingPointRange<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    onSliderValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    startValue: String,
    endValue: String,
    onStartValueChange: (String) -> Unit,
    onEndValueChange: (String) -> Unit
) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(12.dp))
            SmsSlider(
                value = sliderValue,
                valueRange = valueRange
            ) {
                onSliderValueChange(it)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                FilterTextFiled(
                    value = startValue,
                    isHopeSalary = isHopeSalary,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(10.dp))
                        .widthIn(min = 80.dp, max = 100.dp)
                        .height(50.dp)
                        .background(colors.N10)
                ) {
                    onStartValueChange(it)
                }
                FilterTextFiled(
                    value = endValue,
                    isHopeSalary = isHopeSalary,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(10.dp))
                        .widthIn(min = 80.dp, max = 100.dp)
                        .height(50.dp)
                        .background(colors.N10)
                ) {
                    onEndValueChange(it)
                }
            }
        }
    }
}