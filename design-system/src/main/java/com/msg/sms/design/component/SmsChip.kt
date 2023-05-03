package com.msg.sms.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.sms.design_system.R

@Composable
fun SmsChip(
    icon: Int = R.drawable.plus_btn_icon,
    text: String,
    onClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier.clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Chip Component Icon",
                tint = colors.N30
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = text,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.N30
            )
        }
    }
}