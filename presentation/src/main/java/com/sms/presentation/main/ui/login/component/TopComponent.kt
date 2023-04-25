package com.sms.presentation.main.ui.login.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun TopComponent() {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            Text(
                text = "Student\nManagement\nService",
                style = typography.headline1,
                fontWeight = FontWeight(700),
                color = colors.WHITE,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "학생 정보 통합관리 서비스",
                style = typography.title2,
                fontWeight = FontWeight(600),
                color = colors.N20
            )
        }
    }
}