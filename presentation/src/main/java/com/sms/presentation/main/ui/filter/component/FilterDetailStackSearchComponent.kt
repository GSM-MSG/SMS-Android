package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.SearchIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterDetailStackSearchComponent(detailStack: String, onClick: () -> Unit) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "세부스택",
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(16.dp))
            SmsCustomTextField(
                placeHolder = "찾고 싶은 세부스택 입력",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(FocusRequester())
                    .onFocusChanged {
                        if (it.isFocused) {
                            onClick()
                        }
                    },
                setChangeText = detailStack,
                readOnly = true,
                endIcon = null,
                leadingIcon = { SearchIcon() },
                onValueChange = {},
                clickAction = {}
            )
        }
    }
}