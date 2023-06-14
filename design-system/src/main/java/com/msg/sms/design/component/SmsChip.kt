package com.msg.sms.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.theme.color.LightColor
import com.sms.design_system.R

@Composable
fun SmsChip(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_plus_btn_gray,
    text: String,
    onClick: () -> Unit
) {
    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor)
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 5.5.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                backgroundColor = LightColor.N20
                                onClick()
                                this.awaitRelease()
                                backgroundColor = Color.Transparent
                            },
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Chip Component Icon",
                    tint = colors.N30
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = text,
                    style = typography.body1,
                    fontWeight = FontWeight.Normal,
                    color = colors.N30
                )
            }
        }
    }
}

@Preview
@Composable
fun SmsChipPre() {
    SmsChip(text = "text") {

    }
}