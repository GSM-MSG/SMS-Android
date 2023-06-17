package com.msg.sms.design.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    text: String,
    leftIcon: @Composable (() -> Unit)?,
    rightIcon: @Composable (() -> Unit)?,
    onClickRightButton: (() -> Unit) = {},
    onClickLeftButton: (() -> Unit) = {},
) {
    SMSTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            if (leftIcon != null) {
                IconButton(
                    onClick = onClickLeftButton,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    leftIcon()
                }
            }
            Text(
                text = text,
                style = typography.title2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 16.dp)
            )
            if (rightIcon != null) {
                IconButton(
                    onClick = onClickRightButton,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    rightIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun TopBarPre() {
    TopBarComponent(
        text = "정보입력",
        leftIcon = { BackButtonIcon() },
        onClickRightButton = { },
        rightIcon = null
    )
}