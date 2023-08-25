package com.msg.sms.design.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.SmallPlusIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ListAddButton(onClick: () -> Unit) {
    SMSTheme { colors, typography ->
        TextButton(onClick = onClick) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SmallPlusIcon()
                Text(
                    text = "추가",
                    style = typography.title2,
                    fontWeight = FontWeight.Bold,
                    color = colors.BLACK
                )
            }
        }
    }
}