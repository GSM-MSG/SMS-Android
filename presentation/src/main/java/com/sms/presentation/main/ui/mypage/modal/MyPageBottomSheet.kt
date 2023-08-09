package com.sms.presentation.main.ui.mypage.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.button.LogoutButton
import com.sms.presentation.main.ui.mypage.component.button.WithdrawalButton

@Composable
fun MyPageBottomSheet(onClickLogout: () -> Unit, onClickWithdrawal: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LogoutButton(onClick = onClickLogout)
            WithdrawalButton(onClick = onClickWithdrawal)
        }
    }
}

@Preview
@Composable
private fun MyPageBottomSheetPre() {
    MyPageBottomSheet(onClickLogout = {}, onClickWithdrawal = {})
}