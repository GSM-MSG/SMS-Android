package com.msg.sms.design.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.smsClickable(
    rippleEnabled: Boolean = true,
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    onClick: () -> Unit
) = composed {
    this.wrapContentSize()
    combinedClickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = if (rippleEnabled) rememberRipple(bounded = bounded, radius = radius) else null
    )
}