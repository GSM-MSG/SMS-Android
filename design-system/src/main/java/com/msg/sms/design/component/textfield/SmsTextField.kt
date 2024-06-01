package com.msg.sms.design.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SmsBasicTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String = "Error",
    assistiveText: String = "",
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
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = text,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = singleLine,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            Text(text = placeHolder, style = typography.body1)
                        },
                        trailingIcon = trailingIcon,
                        leadingIcon = leadingIcon,
                        label = label,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = colors.N10,
                            placeholderColor = colors.N30,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = colors.P2
                        ),
                        contentPadding = PaddingValues(vertical = 13.5.dp, horizontal = 12.dp)
                    )
                }
            )
            if (isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorText, color = colors.ERROR, style = typography.caption1)
            }
            if (assistiveText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = assistiveText, color = Color(0xFFA0ACB1), style = typography.caption1)
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
            IconButton(onClick = onDeleteButtonClick, enabled = text.isNotEmpty()) {
                if (text.isNotEmpty()) DeleteButtonIcon()
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
        errorText = "error text",
        assistiveText = "assistive text"
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