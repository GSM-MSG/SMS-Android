package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.msg.sms.design.theme.SMSTheme

@Composable
fun AddProjectButton(onClick: () -> Unit) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            TextButton(onClick = onClick) {
                Text(
                    text = "+  추가",
                    style = typography.title2,
                    fontWeight = FontWeight.Bold,
                    color = colors.BLACK
                )
            }
        }
    }
}