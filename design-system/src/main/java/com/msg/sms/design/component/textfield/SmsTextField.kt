package com.msg.sms.design.component.textfield

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsTextField(
    modifier: Modifier,
    isError: Boolean,
    placeHolder: String,
    readOnly: Boolean = true,
    isFocus: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    SMSTheme { colors, typography ->
        TextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            placeholder = {
                Text(text = placeHolder, style = typography.body1)
            },
            modifier = modifier,
            textStyle = typography.body1,
        )
    }
}