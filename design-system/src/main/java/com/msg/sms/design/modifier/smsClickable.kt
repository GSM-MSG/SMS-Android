package com.msg.sms.design.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.smsClickable(
    rippleEnabled: Boolean = true,
    bounded: Boolean = true,
    radius: Dp = 15.dp,
    onClick: () -> Unit
) = composed {
    combinedClickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = if (rippleEnabled) rememberRipple(bounded = bounded, radius = radius) else null
    )
}