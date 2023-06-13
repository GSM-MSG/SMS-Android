package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.RedLogoutIcon
import com.msg.sms.design.icon.RedWithdrawalIcon

@Composable
fun LogoutWithDrawalBottomSheetComponent(
    onLogoutClick: () -> Unit,
    onWithDrawalClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(24.dp))
    ModalBottomSheetItem(
        text = "로그아웃",
        icon = {
            RedLogoutIcon(
                modifier = Modifier.padding(12.dp)
            )
        },
        onClick = onLogoutClick
    )
    Spacer(modifier = Modifier.height(8.dp))
    ModalBottomSheetItem(
        text = "회원탈퇴",
        icon = {
            RedWithdrawalIcon(
                modifier = Modifier.padding(12.dp)
            )
        },
        onClick = onWithDrawalClick
    )
    Spacer(modifier = Modifier.height(16.dp))
}