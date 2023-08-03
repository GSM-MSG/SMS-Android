package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilloutStatusProgressBar(currentRoute: String?) {
    var progress by remember { mutableStateOf(0f) }

    progress = when (currentRoute) {
        "SchoolLife" -> 0.16f
        "WorkCondition" -> 0.3f
        "MilitaryService" -> 0.44f
        "Certification" -> 0.575f
        "ForeignLanguage" -> 0.72f
        "Search" -> 0.85f
        "" -> 1f
        else -> 0.025f
    } // TODO 프로젝트 입력 페이지 추가되면 "" 공백에 프로젝트 Route 등록

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 100,
            easing = LinearOutSlowInEasing
        )
    )

    SMSTheme { colors, _ ->
        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(colors.N10)
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                (1..8).forEach { _ ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(colors.N20)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .height(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(colors.P2)
                    .animateContentSize()
                    .align(Alignment.CenterStart)
            )
        }
    }
}