package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsBasicTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String = "Error",
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
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
                value = text,
                onValueChange = onValueChange,
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
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                readOnly = readOnly,
                label = label
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
        }
    }
}

@Composable
fun SmsTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String = "Error",
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit = {},
    onTrailingIconClick: () -> Unit,
) {
    SmsBasicTextField(
        modifier = modifier,
        text = text,
        placeHolder = placeHolder,
        readOnly = readOnly,
        isError = isError,
        errorText = errorText,
        maxLines = maxLines,
        singleLine = singleLine,
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = onTrailingIconClick, enabled = text.isNotEmpty()) {
                if (text.isNotEmpty()) DeleteButtonIcon()
            }
        }
    )
}

@Composable
fun SmsCustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    isError: Boolean = false,
    errorText: String = "Error",
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
) {
    SmsBasicTextField(
        modifier = modifier,
        text = text,
        placeHolder = placeHolder,
        isError = isError,
        errorText = errorText,
        readOnly = readOnly,
        singleLine = singleLine,
        focusRequester = focusRequester,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        onValueChange = onValueChange
    )
}

@Composable
fun FilterTextFiled(
    value: String,
    modifier: Modifier = Modifier,
    isHopeSalary: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    SmsBasicTextField(
        modifier = modifier,
        text = if (isHopeSalary) "$value 만원" else value,
        onValueChange = {
            onValueChange(it.replace("\\D".toRegex(), ""))
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
fun NoneIconTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    SmsBasicTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        placeHolder = placeHolder,
        readOnly = readOnly,
        focusRequester = focusRequester,
        singleLine = singleLine
    )
}