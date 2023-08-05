package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.icon.SearchIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun DisplaySearchBar(
    modifier: Modifier = Modifier,
    placeHolder: String = "OOTD 하나까지 완전 우리답지~",
    focusRequest: FocusRequester = FocusRequester(),
) {
    SMSTheme { colors, typography ->
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequest),
            value = "",
            onValueChange = {},
            leadingIcon = { SearchIcon() },
            placeholder = {
                Text(
                    text = placeHolder,
                    style = typography.body1,
                    fontWeight = FontWeight.Normal
                )
            },
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colors.N10,
                placeholderColor = colors.N30,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
private fun DisplaySearchBarPre() {
    DisplaySearchBar()
}