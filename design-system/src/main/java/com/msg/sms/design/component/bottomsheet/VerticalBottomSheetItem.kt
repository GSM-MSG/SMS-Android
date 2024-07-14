package com.msg.sms.design.component.bottomsheet

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun VerticalBottomSheetItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    leftIcon: @Composable () -> Unit,
) {
    SMSTheme { colors, typography ->
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val buttonColor by animateColorAsState(
            if (isPressed) colors.N10 else colors.WHITE,
            label = "",
        )
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(color = buttonColor)
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null
                )
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            leftIcon()
            Text(
                text = text,
                style = typography.title2,
                color = colors.N50
            )
        }
    }
}

@Preview
@Composable
fun VerticalBottomSheetItemPreview() {
    VerticalBottomSheetItem(
        text = "Text",
        onClick = {},
        leftIcon = { BackButtonIcon() }
    )
}