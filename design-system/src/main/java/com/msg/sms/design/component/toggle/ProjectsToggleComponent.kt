package com.msg.sms.design.component.toggle

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.CancelIcon
import com.msg.sms.design.icon.ToggleArrowIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ToggleComponent(
    name: String,
    onCancelButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val contentVisible = remember {
        mutableStateOf(false)
    }

    SMSTheme { colors, typography ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Text(
                    text = name,
                    style = typography.title1,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ToggleArrowIcon(modifier = Modifier.smsClickable {
                        contentVisible.value = !contentVisible.value
                    })
                    Spacer(modifier = Modifier.width(16.dp))
                    CancelIcon(modifier = Modifier.smsClickable(onClick = onCancelButtonClick))
                }
            }
            if (contentVisible.value) content()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                color = colors.N20,
                thickness = 1.dp
            )
        }
    }
}