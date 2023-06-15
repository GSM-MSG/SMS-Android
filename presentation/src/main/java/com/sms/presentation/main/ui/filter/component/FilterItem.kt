package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsCheckBox
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterItem(
    item: Pair<String, Boolean>,
    isChecked: (checked: Boolean, text: String) -> Unit
) {
    val checked = remember {
        mutableStateOf(item.second)
    }

    SMSTheme { colors, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            SmsCheckBox(checked = checked.value) {
                checked.value = !checked.value
                isChecked(checked.value, item.first)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item.first,
                style = typography.body2,
                fontWeight = FontWeight.Normal,
                color = if (checked.value) colors.BLACK else colors.N30
            )
        }
    }
}

@Preview
@Composable
fun FilterItemPre() {
    FilterItem(Pair("Android", false)) { checked, text ->

    }
}