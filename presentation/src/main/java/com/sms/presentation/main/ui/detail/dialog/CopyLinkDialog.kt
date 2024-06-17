package com.sms.presentation.main.ui.detail.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.theme.SMSTheme

@Composable
fun CopyLinkDialog(
    widthPercent: Float? = null,
    widthDp: Dp? = null,
    heightPercent: Float? = null,
    heightDp: Dp? = null,
    betweenTextAndButtonHeight: Dp? = null,
    cancelButtonEnabled: Boolean = false,
    isError: Boolean = false,
    title: String,
    outLineButtonText: String,
    importantButtonText: String,
    outlineButtonOnClick: () -> Unit,
    importantButtonOnClick: () -> Unit,
    onDismissRequest: () -> Unit = {},
    token: String,
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

    val clipBoardManager = LocalClipboardManager.current

    SMSTheme { colors, typography ->
        Dialog(
            onDismissRequest = { onDismissRequest() }
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
                    Spacer(modifier = Modifier.size(24.dp))
                    Row (
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, color = colors.N20, shape = RoundedCornerShape(12.dp))
                            .background(color = colors.N10)
                            .fillMaxWidth()
                            .fillMaxHeight(0.39f)
                    ){
                        Text(
                            text = "https://sms.msg-team.com/student/link?token=$token",
                            modifier = Modifier
                                .padding(vertical = 13.5.dp, horizontal = 12.dp)
                                .fillMaxWidth(0.65f),
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { clipBoardManager.setText(AnnotatedString("https://sms.msg-team.com/student/link?token=$token")) },
                            modifier = Modifier
                                .padding(end = 12.dp, top = 8.5.dp, bottom = 8.5.dp),
                            border = BorderStroke(1.dp, colors.P2),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = colors.P2, backgroundColor = colors.N10),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "복사",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = 14.dp, vertical = 5.dp),
                                color = colors.P2
                            )
                        }
                    }
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
                            .fillMaxWidth(0.485f)
                            .align(Alignment.CenterEnd),
                        onClick = importantButtonOnClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LinkDialogPre() {
    CopyLinkDialog(
        title = "만료일 선택",
        outLineButtonText = "",
        importantButtonText = "확인",
        outlineButtonOnClick = { /*TODO*/ },
        importantButtonOnClick = { /*TODO*/ },
        widthDp = 328.dp,
        heightDp = 226.dp,
        token = "",
    )
}