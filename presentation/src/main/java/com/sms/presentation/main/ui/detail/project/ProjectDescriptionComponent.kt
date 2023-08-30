package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddBody1Title

@Composable
fun ProjectDescriptionComponent(description: String) {
    AddBody1Title(titleText = "프로젝트 설명", spaceSize = 8, modifier = Modifier.heightIn(max = 1000.dp)) {
        SMSTheme { colors, typography ->
            Text(
                text = description,
                style = typography.body2,
                color = colors.N40,
                fontWeight = FontWeight.Normal
            )
        }
    }
}