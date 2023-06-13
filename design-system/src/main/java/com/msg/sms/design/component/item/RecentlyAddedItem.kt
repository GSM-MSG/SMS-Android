package com.msg.sms.design.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun RecentlyAddedItem(
    stack: String,
    selectedStack: List<String>,
    onClick: (stack: String, checked: Boolean) -> Unit,
) {
    SMSTheme { colors, typography ->
        val checked = selectedStack.contains(stack)
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stack,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.N50
            )
            SmsCheckBox(checked = checked) {
                onClick(stack, checked)
            }
        }
    }
}

@Preview
@Composable
fun RecentlyAddedItemPre() {
    RecentlyAddedItem(stack = "aaa", selectedStack = listOf("false")) { _, _ -> }
}