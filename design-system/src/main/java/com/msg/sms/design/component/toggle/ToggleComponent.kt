package com.msg.sms.design.component.toggle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.CloseIcon
import com.msg.sms.design.icon.ToggleIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ToggleComponent(
    name: String,
    onCancelButtonClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val contentVisible = remember {
        mutableStateOf(false)
    }
    val currentRotation = remember {
        mutableStateOf(0f)
    }
    val rotation = remember {
        Animatable(currentRotation.value)
    }

    LaunchedEffect(contentVisible.value) {
        rotation.animateTo(
            targetValue =
            if (contentVisible.value) currentRotation.value - 90f
            else if (currentRotation.value != 0f) currentRotation.value + 90f
            else currentRotation.value,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        ) {
            currentRotation.value = value
        }
    }

    SMSTheme { colors, typography ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = name,
                    style = typography.title1,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ToggleIcon(
                        modifier = Modifier
                            .rotate(rotation.value)
                            .smsClickable {
                                contentVisible.value = !contentVisible.value
                            }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CloseIcon(modifier = Modifier.smsClickable(onClick = onCancelButtonClick))
                }
            }
            AnimatedVisibility(visible = contentVisible.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .animateContentSize()
                ) {
                    content()
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 4.dp),
                color = colors.N20,
                thickness = 1.dp
            )
        }
    }
}