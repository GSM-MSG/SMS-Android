package com.msg.sms.design.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.modifier.smsClickable
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
            BasicTextField(
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
                value = text,
                onValueChange = onValueChange,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                maxLines = maxLines,
                singleLine = singleLine,
                textStyle = typography.body1,
                readOnly = readOnly,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(colors.N10)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(vertical = 13.5.dp, horizontal = 12.dp)
                        ) {
                            innerTextField()
                            if (text.isEmpty()) {
                                Text(
                                    text = placeHolder,
                                    color = colors.N30,
                                    style = typography.body1,
                                )
                            }
                        }
                        if (trailingIcon != null) {
                            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                                trailingIcon()
                            }
                        }
                        if (leadingIcon != null) {
                            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                                leadingIcon()
                            }
                        }
                    }
                }
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
        }
    }
}

@Composable
fun SmsOnlyInputTextField(
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
    onValueChange: (String) -> Unit,
    onDeleteButtonClick: () -> Unit,
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
            if (text.isNotEmpty()) {
                DeleteButtonIcon(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .smsClickable(
                            rippleEnabled = false,
                            onClick = onDeleteButtonClick
                        )
                )
            }
        }
    )
}

@Preview
@Composable
fun SmsBasicTextFieldPre() {
    val (text, onValueChange) = remember {
        mutableStateOf("")
    }

    SmsBasicTextField(
        text = text,
        onValueChange = onValueChange,
        isError = true,
        errorText = "error text"
    )
}

@Preview
@Composable
fun SmsTextFieldPre() {
    val (text, onValueChange) = remember {
        mutableStateOf("")
    }

    SmsOnlyInputTextField(
        text = text,
        onValueChange = onValueChange,
        onDeleteButtonClick = {}
    )
}