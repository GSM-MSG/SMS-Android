package com.msg.sms.design.component.header

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    onClickRemoveButton: () -> Unit = {},
    onClickToggleButton: (rotateState: Boolean) -> Unit = {},
) {
    val expandState = remember {
        mutableStateOf(true)
    }
    val rotateState by animateFloatAsState(
        targetValue = if (expandState.value)  0f else 90f
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
                        IconButton(onClick = {
                            expandState.value = !expandState.value
                            Log.d("TAGAT", "${expandState.value}")
                            onClickToggleButton(expandState.value)
                        }
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
    TitleHeader("Test *", isExpandable = true, isRemovable = true)
}