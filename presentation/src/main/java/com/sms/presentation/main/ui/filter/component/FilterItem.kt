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
    text: String,
    isChecked: (checked: Boolean, text: String) -> Unit
) {
    val checked = remember {
        mutableStateOf(false)
    }

    SMSTheme { colors, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            SmsCheckBox(checked = checked.value) {
                checked.value = !checked.value
                isChecked(checked.value, text)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = text,
                style = typography.body2,
                fontWeight = FontWeight.Normal,
                color = colors.N50
            )
        }
    }
}

@Preview
@Composable
fun FilterItemPre() {
    FilterItem("Android") { checked, text ->

    }
}