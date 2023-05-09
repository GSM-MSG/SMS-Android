package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsTextField(
    modifier: Modifier,
    isError: Boolean,
    placeHolder: String,
    readOnly: Boolean = true,
    focusRequester: FocusRequester,
    errorText: String,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    SMSTheme { colors, typography ->
        Column {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                placeholder = {
                    Text(text = placeHolder, style = typography.body1)
                },
                modifier = modifier
                    .border(width = 1.dp, shape = RoundedCornerShape(8.dp), color = colors.N10)
                    .focusRequester(focusRequester),
                textStyle = typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = colors.N10,
                    placeholderColor = colors.N30,
                    focusedBorderColor = colors.P2,
                    unfocusedBorderColor = colors.N10
                )
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
        }
    }
}