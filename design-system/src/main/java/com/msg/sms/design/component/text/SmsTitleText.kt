package com.msg.sms.design.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsTitleText(text: String, isRequired: Boolean) {
    SMSTheme { colors, typography ->
        Row {
            Text(text = text, style = typography.title1, fontWeight = FontWeight.Bold)
            if(isRequired) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = typography.title1,
                    fontWeight = FontWeight.Bold,
                    color = colors.S2
                )
            }
        }
    }
}