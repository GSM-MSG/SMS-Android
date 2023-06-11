package com.sms.presentation.main.ui.login.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun LookAroundToGuestButton(
    onClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Text(
            text = "게스트로 둘러보기",
            style = typography.body2,
            fontWeight = FontWeight.Normal,
            color = colors.WHITE,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.smsClickable(onClick = onClick)
        )
    }
}