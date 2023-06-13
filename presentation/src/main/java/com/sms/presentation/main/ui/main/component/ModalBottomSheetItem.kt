package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.theme.color.LightColor

@Composable
fun ModalBottomSheetItem(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            backgroundColor = LightColor.N10
                            this.awaitRelease()
                            backgroundColor = Color.Transparent
                            onClick()
                        },
                    )
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(backgroundColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon()
                Text(
                    text = text,
                    style = typography.body1,
                    color = colors.ERROR,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}