package com.sms.presentation.main.ui.detail

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddBody1Title

@Composable
fun ImportantTaskComponent(importantTask: String) {
    AddBody1Title(titleText = "주요 작업 서술", spaceSize = 8, modifier = Modifier.heightIn(max = 1000.dp)) {
        SMSTheme { colors, typography ->
            Text(
                text = importantTask,
                style = typography.body2,
                color = colors.N40,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
private fun ImportantTaskComponentPre() {
    ImportantTaskComponent(importantTask = "저는 안드로이드 앱 개발 파트에서 이이이잉으로 이이이이이잉 했습니다. 히히 나이스")
}