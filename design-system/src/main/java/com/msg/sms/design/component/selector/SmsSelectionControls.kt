package com.msg.sms.design.component.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsSelectionControls(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)?
) {
    SMSTheme { colors, _ ->
        Canvas(
            modifier = modifier
                .width(32.dp)
                .height(32.dp)
                .clickable {
                    if (onClick != null && enabled) {
                        onClick()
                    }
                },
        ) {
            drawCircle(
                color = if (selected) colors.P2 else colors.N20,
                radius = 10.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                color = if (selected) colors.P2 else Color.Transparent,
                radius = 6.dp.toPx()
            )
        }
    }
}

@Preview
@Composable
fun SelectionControlPre() {
    val selected = remember {
        mutableStateOf("0")
    }
    Column(Modifier.background(Color.White)) {
        SmsSelectionControls(selected = selected.value == "0", onClick = { selected.value = "0" })
        Spacer(modifier = Modifier.height(10.dp))
        SmsSelectionControls(selected = selected.value == "1", onClick = { selected.value = "1" })
    }
}