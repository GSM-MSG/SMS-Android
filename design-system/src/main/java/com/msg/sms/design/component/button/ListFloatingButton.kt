package com.msg.sms.design.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.ArrowUpWardIcon

@Composable
fun ListFloatingButton(onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)
            .clip(CircleShape)
            .background(Color.Black)
    ) {
        ArrowUpWardIcon()
    }
}