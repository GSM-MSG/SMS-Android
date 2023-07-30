package com.msg.sms.design.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun TechStackTextComponent(str: String, modifier: Modifier = Modifier) {
    SMSTheme { colors, typography ->
        Box(
            modifier = modifier
                .background(colors.N10)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = str,
                modifier = Modifier
                    .padding(vertical = 6.5.dp, horizontal = 12.dp), style = typography.caption2,
                color = colors.N40,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun TechStackTextComponentPre() {
    TechStackTextComponent(str = "테스트")
}