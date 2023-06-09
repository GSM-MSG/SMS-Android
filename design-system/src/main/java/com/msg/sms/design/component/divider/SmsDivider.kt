package com.msg.sms.design.component.divider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsDivider(modifier: Modifier = Modifier) {
    SMSTheme { colors, _ ->
        Divider(modifier = modifier
            .fillMaxWidth()
            .height(1.dp), color = colors.N20)
    }
}