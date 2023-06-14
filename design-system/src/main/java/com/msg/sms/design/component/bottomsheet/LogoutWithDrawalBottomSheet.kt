package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.RedLogoutIcon
import com.msg.sms.design.icon.RedWithdrawalIcon
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LogoutWithDrawalBottomSheet(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    onLogoutClick: () -> Unit,
    onWithDrawalClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Spacer(modifier = Modifier.height(24.dp))
        BaseBottomSheetComponent(
            text = "로그아웃",
            textStyle = typography.body1,
            textColor = colors.ERROR,
            fontWeight = FontWeight.Normal,
            leftIcon = {
                RedLogoutIcon(
                    modifier = Modifier.padding(12.dp)
                )
            },
            onClick = onLogoutClick,
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState
        )
        Spacer(modifier = Modifier.height(8.dp))
        BaseBottomSheetComponent(
            text = "회원탈퇴",
            textStyle = typography.body1,
            textColor = colors.ERROR,
            fontWeight = FontWeight.Normal,
            leftIcon = {
                RedWithdrawalIcon(
                    modifier = Modifier.padding(12.dp)
                )
            },
            onClick = onWithDrawalClick,
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}