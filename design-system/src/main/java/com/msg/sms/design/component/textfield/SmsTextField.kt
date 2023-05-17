package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
    onValueChange: (String) -> Unit = {}
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
                    IconButton(onClick = { text = "" }) {
                        DeleteButtonIcon()
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
    endIcon: @Composable () -> Unit,
    clickAction: () -> Unit,
    isError: Boolean = false,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setChangeText: String,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    text = setChangeText
    SMSTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
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
                    unfocusedBorderColor = Color.Transparent
                ),
                trailingIcon = {
                    IconButton(onClick = { clickAction() }) {
                        endIcon()
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