package com.sms.presentation.main.ui.detail.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SelectExpirationDateDialog(
    widthPercent: Float? = null,
    widthDp: Dp? = null,
    heightPercent: Float? = null,
    heightDp: Dp? = null,
    betweenTextAndButtonHeight: Dp? = null,
    cancelButtonEnabled: Boolean = true,
    isError: Boolean = false,
    title: String,
    outLineButtonText: String,
    importantButtonText: String,
    outlineButtonOnClick: () -> Unit,
    importantButtonOnClick: () -> Unit,
    onDismissRequest: () -> Unit = {},
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

    var selectedOption by remember { mutableStateOf("30일") }

    val options = listOf("5일", "10일", "15일", "20일", "25일", "30일")

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
                    if (betweenTextAndButtonHeight != null)
                        Spacer(modifier = Modifier.height(betweenTextAndButtonHeight))
                }
                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 50.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                ) {
                    items(options) { text ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = { selectedOption = text },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = colors.P2
                                )
                            )
                            Text(text = text)
                        }
                    }
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

@Preview
@Composable
fun DialogPre() {
    SelectExpirationDateDialog(
        title = "만료기간 선택",
        outLineButtonText = "취소",
        importantButtonText = "링크 생성",
        outlineButtonOnClick = { /*TODO*/ },
        importantButtonOnClick = { /*TODO*/ },
        widthDp = 328.dp,
        heightDp = 280.dp
    )
}