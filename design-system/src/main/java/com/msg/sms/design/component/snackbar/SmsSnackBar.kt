package com.msg.sms.design.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsSnackBar(
    modifier: Modifier = Modifier,
    text: String,
    leftIcon: @Composable (() -> Unit),
    onClick: () -> Unit,
) {
    SMSTheme { colors, typography ->
        Card(
            modifier = modifier
                .smsClickable {
                    onClick()
                },
            shape = RoundedCornerShape(50)
        ) {
            Row(
                modifier = Modifier
                    .background(colors.WHITE)
                    .padding(vertical = 14.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leftIcon()
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = text,
                    style = typography.body1,
                    fontWeight = FontWeight.Normal,
                    color = colors.BLACK
                )
            }
        }
    }
}

@Preview
@Composable
fun SmsSnackBarPre() {
    SmsSnackBar(text = "text", leftIcon = { TrashCanIcon() }) {

    }
}
