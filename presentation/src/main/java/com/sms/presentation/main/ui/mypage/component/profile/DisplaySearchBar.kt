package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.SearchIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun DisplaySearchBar(
    modifier: Modifier = Modifier,
    placeHolder: String = "OOTD 하나까지 완전 우리답지~",
    onSearchBarClick: () -> Unit,
) {
    SMSTheme { colors, typography ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = colors.N10)
                .padding(12.dp)
                .smsClickable(rippleEnabled = false, onClick = onSearchBarClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchIcon()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = placeHolder,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.N30
            )
        }
    }
}

@Preview
@Composable
private fun DisplaySearchBarPre() {
    DisplaySearchBar{}
}