package com.msg.sms.design.component.slider

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import com.msg.sms.design.theme.SMSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmsSlider(
    value: ClosedFloatingPointRange<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    SMSTheme { colors, _ ->
        RangeSlider(value = value,
            valueRange = valueRange,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                activeTrackColor = colors.P2,
                inactiveTrackColor = colors.N10,
                thumbColor = colors.P2
            ),
            startThumb = {
                SmsSliderThumbBox()
            },
            endThumb = {
                SmsSliderThumbBox()
            },
            track = { sliderPositions ->
                SmsSliderCustomTrack(sliderPositions = sliderPositions)
            })
    }
}