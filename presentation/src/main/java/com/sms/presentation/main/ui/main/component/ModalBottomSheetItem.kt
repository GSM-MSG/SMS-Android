package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ModalBottomSheetItem(text: String, icon: @Composable () -> Unit, onClick: () -> Unit) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .smsClickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = typography.body1,
                color = colors.ERROR,
                fontWeight = FontWeight.Normal
            )
        }
    }
}