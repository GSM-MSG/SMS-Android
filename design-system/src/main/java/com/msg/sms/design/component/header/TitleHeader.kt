package com.msg.sms.design.component.header

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    titleText: String,
    isExpandable: Boolean = false,
    isRemovable: Boolean = false,
    isExpand: Boolean = false,
    onClickRemoveButton: () -> Unit = {},
    onClickToggleButton: () -> Unit = {},
) {
    val rotateState by animateFloatAsState(
        targetValue = if (isExpand)  0f else 90f
    )
    SMSTheme { colors, typography ->
        Box(
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp),
                    text = coloredText,
                    style = typography.body1,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    if (isExpandable) {
                        IconButton(onClick = onClickToggleButton
                        ) {
                            ChevronDownIcon(modifier = Modifier.rotate(rotateState))
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
    TitleHeader("Test *", isExpandable = true, isRemovable = true,isExpand = true)
}