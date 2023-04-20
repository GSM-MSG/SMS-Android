package com.msg.sms.design.theme

import androidx.compose.runtime.Composable
import com.msg.sms.design.theme.color.LightColor

@Composable
fun SMSTheme(
    colors: ColorTheme = if(true) LightColor else LightColor,
    typography: SMSTypography = SMSTypography,
    content: @Composable (colors: ColorTheme, typography: SMSTypography) -> Unit
) {
    content(colors, typography)
}