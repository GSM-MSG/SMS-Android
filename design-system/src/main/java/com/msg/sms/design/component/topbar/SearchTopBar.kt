package com.msg.sms.design.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.SearchBar
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.icon.DeleteButtonIcon

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    setText: String,
    onValueChanged: (String) -> Unit,
    debounceTime: Long = 300L,
    debounceTextChanged: (String) -> Unit,
    onClickBackButton: () -> Unit,
    onClickButton: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, bottom = 12.dp, top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClickBackButton, modifier = Modifier.size(24.dp)) {
            BackButtonIcon()
        }
        Spacer(modifier = Modifier.width(22.dp))
        SearchBar(
            modifier = modifier.weight(1f),
            setText = setText,
            onValueChanged = onValueChanged,
            placeHolder = "찾고 싶은 세부 스택 입력",
            trailingIcon = { DeleteButtonIcon() },
            debounceTime = debounceTime,
            debounceTextChanged = debounceTextChanged
        ) {
            onClickButton()
        }
    }
}

@Preview
@Composable
fun SearchTopBarPre() {
    SearchTopBar(
        onValueChanged = {},
        setText = "sd",
        onClickButton = {},
        onClickBackButton = {},
        debounceTextChanged = {}
    )
}