package com.msg.sms.design.component.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SliderPositions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsSliderCustomTrack(sliderPositions: SliderPositions) {
    SMSTheme { colors, _ ->
        val inactiveTrackColor = colors.N10
        val activeTrackColor = colors.P2
        val inactiveTickColor = colors.N10
        val activeTickColor = colors.P2
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
        ) {
            val isRtl = layoutDirection == LayoutDirection.Rtl
            val sliderLeft = Offset(0f, center.y)
            val sliderRight = Offset(size.width, center.y)
            val sliderStart = if (isRtl) sliderRight else sliderLeft
            val sliderEnd = if (isRtl) sliderLeft else sliderRight
            val tickSize = 2.dp.toPx()
            val trackStrokeWidth = 2.dp.toPx()
            drawLine(
                color = inactiveTrackColor,
                start = sliderStart,
                end = sliderEnd,
                strokeWidth = trackStrokeWidth,
                cap = StrokeCap.Round
            )
            val sliderValueEnd = Offset(
                sliderStart.x +
                        (sliderEnd.x - sliderStart.x) * sliderPositions.activeRange.endInclusive,
                center.y
            )

            val sliderValueStart = Offset(
                sliderStart.x +
                        (sliderEnd.x - sliderStart.x) * sliderPositions.activeRange.start,
                center.y
            )

            drawLine(
                color = activeTrackColor,
                start = sliderValueStart,
                end = sliderValueEnd,
                strokeWidth = trackStrokeWidth,
                cap = StrokeCap.Round
            )
            sliderPositions.tickFractions.groupBy {
                it > sliderPositions.activeRange.endInclusive ||
                        it < sliderPositions.activeRange.start
            }.forEach { (outsideFraction, list) ->
                drawPoints(
                    list.map {
                        Offset(lerp(sliderStart, sliderEnd, it).x, center.y)
                    },
                    PointMode.Points,
                    (if (outsideFraction) inactiveTickColor else activeTickColor),
                    tickSize,
                    StrokeCap.Round
                )
            }
        }
    }
}