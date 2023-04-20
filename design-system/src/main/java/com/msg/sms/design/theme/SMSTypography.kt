package com.msg.sms.design.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sms.design_system.R

object SMSTypography {

    private val pretendard = FontFamily(
        Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
        Font(R.font.pretendard_thin, FontWeight.Thin),
        Font(R.font.pretendard_light, FontWeight.Light),
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_bold, FontWeight.Bold),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold),
        Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
        Font(R.font.pretendard_black, FontWeight.Black)
    )

    @Stable
    val headline1 = TextStyle(
        fontFamily = pretendard,
        fontSize = 48.sp,
        lineHeight = 40.sp,
    )

    @Stable
    val headline2 = TextStyle(
        fontFamily = pretendard,
        fontSize = 32.sp,
        lineHeight = 38.sp,
    )

    @Stable
    val headline3 = TextStyle(
        fontFamily = pretendard,
        fontSize = 28.sp,
        lineHeight = 34.sp,
    )

    @Stable
    val title1 = TextStyle(
        fontFamily = pretendard,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = pretendard,
        fontSize = 17.sp,
        lineHeight = 21.sp,
    )

    @Stable
    val body1 = TextStyle(
        fontFamily = pretendard,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )

    @Stable
    val body2 = TextStyle(
        fontFamily = pretendard,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    @Stable
    val caption1 = TextStyle(
        fontFamily = pretendard,
        fontSize = 13.sp,
        lineHeight = 19.sp,
    )

    @Stable
    val caption2 = TextStyle(
        fontFamily = pretendard,
        fontSize = 12.sp,
        lineHeight = 17.sp,
    )
}
