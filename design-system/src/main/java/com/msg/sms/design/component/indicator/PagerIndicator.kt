package com.msg.sms.design.component.indicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    indexOfPointingNumber: Int,
    size: Int
) {
    SMSTheme { colors, _ ->
        LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(size) {
                Canvas(modifier = Modifier.size(8.dp)) {
                    drawCircle(color = if (it == indexOfPointingNumber) colors.P2 else colors.N20)
                }
            }
        }
    }
}

@Preview
@Composable
fun PagerIndicatorPre() {
    PagerIndicator(indexOfPointingNumber = 0, size = 6)
}