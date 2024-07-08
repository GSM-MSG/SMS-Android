package com.msg.sms.design.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.icon.SearchIcon
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    setText: String,
    debounceTime: Long = 300L,
    debounceTextChanged: (String) -> Unit,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable () -> Unit,
    placeHolder: String,
    onClickTrailingButton: () -> Unit,
) {
    LaunchedEffect(key1 = setText) {
        delay(debounceTime)
        debounceTextChanged(setText)
    }

    SmsBasicTextField(
        modifier = modifier.fillMaxWidth(),
        text = setText,
        placeHolder = placeHolder,
        onValueChange = onValueChanged,
        trailingIcon = {
            if (setText.isNotEmpty()) {
                IconButton(onClick = onClickTrailingButton) {
                    trailingIcon()
                }
            }
        },
        leadingIcon = {
            SearchIcon()
        }
    )
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