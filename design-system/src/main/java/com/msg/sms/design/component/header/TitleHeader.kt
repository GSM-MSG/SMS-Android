package com.msg.sms.design.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.ChevronDownIcon
import com.msg.sms.design.icon.CloseIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
// if that component is required just add * on text last
fun TitleHeader(
    modifier: Modifier = Modifier,
    titleText: String,
    isExpandable: Boolean = false,
    isRemovable: Boolean = false,
    isExpand: Boolean = false,
    onClickRemoveButton: () -> Unit = {},
    onClickToggleButton: () -> Unit = {},
) {
    SMSTheme { colors, typography ->
        Box(
            modifier = modifier
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp),
                    text = coloredText,
                    style = typography.body1,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                ) {
                    if (isExpandable) {
                        IconButton(
                            onClick = onClickToggleButton
                        ) {
                            ChevronDownIcon(modifier = Modifier.rotate(90f * (if (isExpand) 0f else 1f)))
                        }
                    }
                    if (isRemovable) {
                        IconButton(onClick = onClickRemoveButton) {
                            CloseIcon()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TitleHeaderPre() {
    TitleHeader(titleText = "Test *", isExpandable = true, isRemovable = true, isExpand = true)
}