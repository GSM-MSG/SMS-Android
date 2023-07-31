package com.msg.sms.design.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsCheckBox
import com.msg.sms.design.theme.SMSTheme

@Composable
fun RecentlyAddedItem(
    searchQuery: String,
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
            val list = stack.split(searchQuery)
            val changedList = list.mapIndexed { index, it -> if (it == "" && index != list.lastIndex) searchQuery else it }
            val coloredText = buildAnnotatedString {
                changedList.forEach {
                    withStyle(style = SpanStyle(color = if (it == searchQuery) colors.P2 else colors.BLACK)) {
                        append(it)
                    }
                }
            }
            Text(
                text = coloredText,
                style = typography.body1,
                fontWeight = FontWeight.Normal
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
    RecentlyAddedItem(stack = "aaa", selectedStack = listOf("false"), searchQuery = "") { _, _ -> }
}