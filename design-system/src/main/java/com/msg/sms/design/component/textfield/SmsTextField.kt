package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsTextField(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    SMSTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                keyboardOptions = keyboardOptions,
                placeholder = {
                    Text(text = placeHolder, style = typography.body1)
                },
                modifier = modifier
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = if (isFocused.value) colors.P2 else colors.N10,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                textStyle = typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = colors.N10,
                    placeholderColor = colors.N30,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P2
                ),
                trailingIcon = {
                    IconButton(onClick = { text = "" }, enabled = text.isNotEmpty())
                    {
                        if (text.isNotEmpty()) DeleteButtonIcon()
                    }
                },
                readOnly = readOnly
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
        }
    }
}

@Composable
fun SmsCustomTextField(
    modifier: Modifier = Modifier,
    endIcon: @Composable (() -> Unit)?,
    clickAction: () -> Unit,
    isError: Boolean = false,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setChangeText: String,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    text = setChangeText
    SMSTheme { colors, typography ->
        Column(modifier = modifier) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                placeholder = {
                    Text(text = placeHolder, style = typography.body1)
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = if (isFocused.value) colors.P2 else colors.N10,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .fillMaxWidth(),
                textStyle = typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = colors.N10,
                    placeholderColor = colors.N30,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                trailingIcon = {
                    if (endIcon != null) {
                        IconButton(onClick = { clickAction() }) {
                            endIcon()
                        }
                    }
                },
                readOnly = readOnly
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
        }
    }
}

@Preview
@Composable
fun SmsTextFieldPre() {
    SmsTextField(
        modifier = Modifier
            .height(48.dp)
            .width(200.dp),
        placeHolder = "Test",
        isError = true
    )
}