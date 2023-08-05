package com.msg.sms.design.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.CloseIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun DetailTechStackItem(stack: String, ) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = colors.N10),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 12.dp),
                text = stack,
                style = typography.body2,
                fontWeight = FontWeight.Normal,
                color = colors.N50
            )
            Spacer(modifier = Modifier.width(4.dp))
            CloseIcon(modifier = Modifier.padding(end = 8.dp))
        }
    }
}

@Preview
@Composable
private fun DetailTechStackItemPre() {
    DetailTechStackItem(stack = "Adobe Photoshop")
}