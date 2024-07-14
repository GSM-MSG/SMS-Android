package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.bottomsheet.VerticalBottomSheetItem
import com.msg.sms.design.icon.BriefcaseIcon
import com.msg.sms.design.icon.MyProfileIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ProfileBottomSheet(
    modifier: Modifier = Modifier,
    onProfileOpen: () -> Unit,
    onAuthenticationOpen: () -> Unit,
) {
    SMSTheme { colors, _ ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(color = colors.WHITE)
                .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalBottomSheetItem(text = "마이페이지", onClick = onProfileOpen, leftIcon = {
                MyProfileIcon()
            })
            VerticalBottomSheetItem(text = "인증제", onClick = onAuthenticationOpen, leftIcon = {
                BriefcaseIcon()
            })
        }
    }
}

@Preview
@Composable
fun ProfileBottomSheetPreview() {
    ProfileBottomSheet(onProfileOpen = {}, onAuthenticationOpen = {})
}