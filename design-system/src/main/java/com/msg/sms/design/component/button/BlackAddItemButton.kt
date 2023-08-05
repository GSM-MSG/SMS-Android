package com.msg.sms.design.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.SmallPlusIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun BlackAddItemButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .smsClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SmallPlusIcon()
        SMSTheme { colors, typography ->
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = "추가",
                style = typography.title2,
                fontWeight = FontWeight.Normal,
                color = colors.BLACK
            )
        }
    }
}

@Preview
@Composable
fun BlackAddItemButtonPre() {
    BlackAddItemButton(onClick = {})
}

