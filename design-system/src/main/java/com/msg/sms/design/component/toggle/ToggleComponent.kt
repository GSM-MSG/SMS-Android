package com.msg.sms.design.component.toggle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.ChevronDownIcon
import com.msg.sms.design.icon.CloseIcon
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
        mutableStateOf(90f)
    }
    val rotation = remember {
        Animatable(currentRotation.value)
    }

    LaunchedEffect(contentVisible.value) {
        rotation.animateTo(
            targetValue =
            if (contentVisible.value) currentRotation.value - 90f
            else if (currentRotation.value != 90f) currentRotation.value + 90f
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
                    .padding(top = 20.dp)
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
                    IconButton(onClick = { contentVisible.value = !contentVisible.value }) {
                        ChevronDownIcon(modifier = Modifier.rotate(rotation.value))
                    }
                    IconButton(onClick = onCancelButtonClick) {
                        CloseIcon()
                    }
                }
            }
            AnimatedVisibility(visible = contentVisible.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    content()
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 4.dp),
                color = colors.N20,
                thickness = 1.dp
            )
        }
    }
}