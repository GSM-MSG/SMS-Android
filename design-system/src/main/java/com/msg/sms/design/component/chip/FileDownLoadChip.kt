package com.msg.sms.design.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.theme.color.LightColor

@Composable
fun FileDownLoadChip(
    modifier: Modifier = Modifier,
    text: String = "다운로드",
    onClick: () -> Unit
) {
    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    SMSTheme { _, typography ->
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            backgroundColor = LightColor.N20
                            onClick()
                            this.awaitRelease()
                            backgroundColor = Color.Transparent
                        }
                    )
                }
                    .padding(horizontal = 8.dp, vertical = 5.5.dp)
            ) {
                Text(
                    text = text,
                    fontFamily = typography.pretendard,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun FileDownChipPre() {
    FileDownLoadChip(onClick = {})
}