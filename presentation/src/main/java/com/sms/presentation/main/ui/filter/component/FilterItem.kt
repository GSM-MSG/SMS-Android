package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsCheckBox
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterItem(
    item: String,
    checked: Boolean,
    onItemClick: (checked: Boolean, text: String) -> Unit
) {
    SMSTheme { colors, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            SmsCheckBox(checked = checked) {
                onItemClick(checked, item)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item,
                style = typography.body2,
                fontWeight = FontWeight.Normal,
                color = if (checked) colors.BLACK else colors.N30
            )
        }
    }
}

@Preview
@Composable
fun FilterItemPre() {
    FilterItem(item = "Pre", checked = true) { _, _ -> }
}