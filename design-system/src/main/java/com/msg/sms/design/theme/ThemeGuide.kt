package com.msg.sms.design.theme

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ThemeGuide() {
    SMSTheme() { colors, typography ->
        Button(
            onClick = { /* anything ... */ },
            colors = ButtonDefaults.buttonColors(colors.POSITIVE)
        ) {
            Text(text = "text", style = typography.body1)
        }
    }
}