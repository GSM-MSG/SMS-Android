package com.msg.sms.design.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SmsBoxButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    state: ButtonState = ButtonState.Normal,
    onClick: () -> Unit,
) {
    SMSTheme { colors, typography ->

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val backgroundFor: (buttonState: ButtonState) -> Color = {
            when (it) {
                ButtonState.OutLine -> if (isPressed) colors.N10 else colors.WHITE
                ButtonState.Normal -> if (isPressed) colors.P3 else colors.P2
                ButtonState.Error -> if (isPressed) colors.ERROR else colors.ERROR
            }
        }

        val contentFor: (buttonState: ButtonState) -> Color = {
            when (it) {
                ButtonState.OutLine -> colors.BLACK
                ButtonState.Normal -> colors.WHITE
                ButtonState.Error -> colors.WHITE
            }
        }

        Button(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color =
                    if (state == ButtonState.OutLine)
                        if (isPressed) colors.N30 else colors.N20
                    else colors.WHITE.copy(
                        alpha = 0f
                    )
                ),
            interactionSource = interactionSource,
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundFor(state),
                contentColor = contentFor(state),
                disabledBackgroundColor = colors.N20,
                disabledContentColor = colors.N30
            ),
            shape = RectangleShape,
            contentPadding = PaddingValues(vertical = 13.5.dp)
        ) {
            Text(
                text = text,
                style = typography.title2,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun BoxButtonPre() {
    Column(
        modifier = Modifier.height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SmsBoxButton(
            text = "Text",
            modifier = Modifier
                .width(200.dp)
                .height(48.dp),
            state = ButtonState.Normal
        ) {

        }

        SmsBoxButton(
            text = "Text",
            modifier = Modifier
                .width(200.dp)
                .height(48.dp),
            state = ButtonState.OutLine
        ) {

        }

        SmsBoxButton(
            text = "Text",
            modifier = Modifier
                .width(200.dp)
                .height(48.dp),
            enabled = false
        ) {

        }
    }
}