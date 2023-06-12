package com.msg.sms.design.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.icon.SearchIcon
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    setText: String,
    focusRequester: FocusRequester = FocusRequester(),
    debounceTime: Long = 300L,
    debounceTextChanged: (String) -> Unit,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable () -> Unit,
    placeHolder: String,
    onClickTrailingButton: () -> Unit,
) {
    val isFocused = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = setText) {
        val debounce = kotlinx.coroutines.Job()

        delay(debounceTime)
        debounceTextChanged(setText)

        debounce.cancel()
    }

    SMSTheme { colors, typography ->
        OutlinedTextField(
            modifier = modifier
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
            value = setText,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = placeHolder, style = typography.body1)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colors.N10,
                placeholderColor = colors.N30,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(onClick = { onClickTrailingButton() }) {
                    trailingIcon()
                }
            },
            leadingIcon = { SearchIcon() }
        )
    }
}

@Preview
@Composable

fun SearchBarPre() {
    SearchBar(
        modifier = Modifier,
        setText = "",
        onValueChanged = {},
        trailingIcon = { DeleteButtonIcon() },
        placeHolder = "찾고 싶은 세부 스택 입력",
        debounceTextChanged = {}
    ) {

    }
}