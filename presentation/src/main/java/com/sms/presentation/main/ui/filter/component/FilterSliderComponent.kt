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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.slider.SmsSlider
import com.msg.sms.design.component.textfield.FilterTextFiled
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterSliderComponent(
    title: String,
    textFieldWidth: Dp,
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
                .padding(20.dp)
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
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(10.dp))
                        .width(textFieldWidth)
                        .height(50.dp)
                        .background(colors.N10)
                ) {
                    onStartValueChange(it)
                }
                FilterTextFiled(
                    value = endValue,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(10.dp))
                        .width(textFieldWidth)
                        .height(50.dp)
                        .background(colors.N10)
                ) {
                    onEndValueChange(it)
                }
            }
        }
    }
}