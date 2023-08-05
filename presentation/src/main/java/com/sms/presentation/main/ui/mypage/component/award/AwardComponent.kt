package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.divider.SmsDivider

@Composable
fun AwardComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AwardNameComponent()
        AwardTypeComponent()
        AwardDateComponent()
        SmsDivider()
    }
}

@Preview
@Composable
private fun AwardComponentPre() {
    AwardComponent()
}