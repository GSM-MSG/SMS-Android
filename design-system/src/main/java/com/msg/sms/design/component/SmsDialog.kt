package com.msg.sms.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsDialog(
    widthPercent: Float? = null,
    widthDp: Dp? = null,
    heightPercent: Float? = null,
    heightDp: Dp? = null,
    betweenTextAndButtonHeight: Dp? = null,
    cancelButtonEnabled: Boolean = true,
    isError: Boolean = false,
    title: String,
    msg: String,
    outLineButtonText: String,
    importantButtonText: String,
    outlineButtonOnClick: () -> Unit,
    importantButtonOnClick: () -> Unit,
    onDisMissRequest: () -> Unit = {},
) {
    val modifier = when {
        widthPercent != null && heightPercent != null -> {
            Modifier
                .fillMaxWidth(widthPercent)
                .fillMaxHeight(heightPercent)
        }

        widthDp != null && heightDp != null -> {
            Modifier
                .width(widthDp)
                .height(heightDp)
        }

        else -> {
            Modifier
                .width(330.dp)
                .height(180.dp)
        }
    }

    SMSTheme { colors, typography ->
        Dialog(
            onDismissRequest = { onDisMissRequest() }
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.WHITE)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(24.dp)
                ) {
                    Text(
                        text = title,
                        style = typography.title2,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = msg,
                        style = typography.body1,
                        fontWeight = FontWeight.Normal
                    )
                    if (betweenTextAndButtonHeight != null)
                        Spacer(modifier = Modifier.height(betweenTextAndButtonHeight))
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    if (cancelButtonEnabled) {
                        SmsRoundedButton(
                            text = outLineButtonText,
                            state = ButtonState.OutLine,
                            modifier = Modifier
                                .fillMaxWidth(0.485f)
                                .align(Alignment.CenterStart),
                            onClick = outlineButtonOnClick
                        )
                    }
                    SmsRoundedButton(
                        text = importantButtonText,
                        state = if (isError) ButtonState.Error else ButtonState.Normal,
                        modifier = Modifier
                            .fillMaxWidth(if (cancelButtonEnabled) 0.485f else 1f)
                            .align(Alignment.CenterEnd),
                        onClick = importantButtonOnClick
                    )
                }
            }
        }
    }
}