package com.sms.presentation.main.ui.mypage.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.GrayLogoutIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun LogoutButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .smsClickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GrayLogoutIcon(modifier = Modifier.padding(start = 12.dp, top = 13.5.dp, bottom = 13.5.dp))
        SMSTheme { colors, typography ->
            Text(
                text = "로그아웃",
                style = typography.title2,
                fontWeight = FontWeight.Bold,
                color = colors.BLACK
            )
        }
    }
}

@Preview
@Composable
private fun LogoutButtonPre() {
    LogoutButton() {}
}