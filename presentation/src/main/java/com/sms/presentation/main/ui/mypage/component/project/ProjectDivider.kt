package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.divider.SmsDivider

@Composable
fun ProjectDivider() {
    Column {
        Spacer(modifier = Modifier.height(16.dp).padding(end = 20.dp))
        SmsDivider()
    }
}