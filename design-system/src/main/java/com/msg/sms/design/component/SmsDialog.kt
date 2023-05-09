package com.msg.sms.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.theme.SMSTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SmsDialog(
    widthPercent: Float? = null,
    widthDp: Dp? = null,
    heightPercent: Float? = null,
    heightDp: Dp? = null,
    title: String,
    msg: String,
    outlineButtonOnClick: () -> Unit,
    normalButtonOnClick: () -> Unit,
    onDissMissRequest: () -> Unit = {}
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
            onDismissRequest = { onDissMissRequest() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
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
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    SmsRoundedButton(
                        text = "Text",
                        state = ButtonState.OutLine,
                        modifier = Modifier
                            .fillMaxWidth(0.485f)
                            .height(48.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        outlineButtonOnClick()
                    }
                    SmsRoundedButton(
                        text = "Text",
                        modifier = Modifier
                            .fillMaxWidth(0.485f)
                            .height(48.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        normalButtonOnClick()
                    }
                }
            }
        }
    }
}