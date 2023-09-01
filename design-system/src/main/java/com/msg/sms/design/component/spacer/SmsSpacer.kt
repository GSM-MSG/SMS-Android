package com.msg.sms.design.component.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Preview
@Composable
fun SmsSpacer(height: Int = 16) {
    SMSTheme { colors, _ ->
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(colors.N10))
    }
}