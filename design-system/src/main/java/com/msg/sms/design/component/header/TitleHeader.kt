package com.msg.sms.design.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
// if that component is required just add * on text last
fun TitleHeader(titleText: String) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            val coloredText = buildAnnotatedString {
                titleText.forEach {
                    withStyle(style = SpanStyle(color = if (it == '*') colors.S2 else colors.BLACK)) {
                        append(it)
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 16.dp, start = 20.dp),
                text = coloredText,
                style = typography.body1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun TitleHeaderPre() {
    TitleHeader("Test *")
}