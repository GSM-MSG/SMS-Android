package com.msg.sms.design.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun TopBarComponent(onClickBackButton: () -> Unit, text: String) {
    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(colors.WHITE)
        ) {
            IconButton(
                onClick = onClickBackButton,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                BackButtonIcon()
            }
            Text(text = text, style = typography.title2, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun TopBarPre() {
    TopBarComponent(onClickBackButton = { /*TODO*/ }, text = "정보입력")
}