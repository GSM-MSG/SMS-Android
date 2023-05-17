package com.msg.sms.design.component.selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun MajorSelector(major: String, selected: Boolean, onClick: () -> Unit) {
    SMSTheme { _, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = (16).dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                }
        ) {
            Text(
                text = major,
                style = typography.body1,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            SmsSelectionControls(
                selected = selected,
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick =  {
                    onClick()
                }
            )
        }
    }
}