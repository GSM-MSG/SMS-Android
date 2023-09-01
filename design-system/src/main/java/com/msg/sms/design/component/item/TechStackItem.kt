package com.msg.sms.design.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun TechStackItem(techStack: String) {
    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp))
                .background(colors.N10)
        ) {
            Text(
                text = techStack,
                style = typography.caption2,
                fontWeight = FontWeight.Normal,
                color = colors.N40,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 6.5.dp, horizontal = 12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun TechStackItemPre() {
    TechStackItem(techStack = "aa")
}
@Preview
@Composable
private fun TechStackItemLongTextPre() {
    TechStackItem(techStack = "Android Studio")
}