package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onClickButton: () -> Unit,
) {
    val isFocused = remember {
        mutableStateOf(false)
    }

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus()
        }
    }

    SMSTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = setText,
                onValueChange = {
                    onValueChange(it)
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
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
                maxLines = maxLines,
                singleLine = singleLine,
                textStyle = typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = colors.N10,
                    placeholderColor = colors.N30,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P2
                ),
                trailingIcon = {
                    IconButton(onClick = onClickButton, enabled = setText.isNotEmpty())
                    {
                        if (setText.isNotEmpty()) DeleteButtonIcon()
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
    leadingIcon: @Composable (() -> Unit)? = null,
    clickAction: () -> Unit,
    isError: Boolean = false,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "Error",
    setChangeText: String,
    singleLine: Boolean = false,
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
                singleLine = singleLine,
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
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P2
                ),
                leadingIcon = leadingIcon,
                trailingIcon = {
                    if (endIcon != null) {
                        IconButton(onClick = clickAction) {
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

@Composable
fun FilterTextFiled(
    value: String,
    modifier: Modifier = Modifier,
    isHopeSalary: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    val isFocused = remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    SMSTheme { colors, typography ->
        OutlinedTextField(
            value = if (isHopeSalary) "$value 만원" else value,
            modifier = modifier
                .focusRequester(focusRequester)
                .border(
                    width = 1.dp,
                    color = if (isFocused.value) colors.P2 else colors.N10,
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged {
                    isFocused.value = it.isFocused
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colors.N10,
                placeholderColor = colors.N30,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = colors.P2
            ),
            label = null,
            textStyle = typography.body1,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                onValueChange(it.replace("\\D".toRegex(), ""))
            }
        )
    }
}

@Composable
fun NoneIconTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    setChangeText: String,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    text = setChangeText
    SMSTheme { colors, typography ->
        Box(modifier = modifier) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                singleLine = singleLine,
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
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.P2
                ),
                readOnly = readOnly
            )
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
        isError = true,
        onClickButton = {},
        onValueChange = {},
        setText = ""
    )
}