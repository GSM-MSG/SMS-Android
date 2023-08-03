package com.msg.sms.design.component.progressbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilloutStatusProgressBar(routeList: List<String>, currentRoute: String?) {
    val progress = remember { mutableStateOf(0.dp) }
    val dotLocationInfoList = remember {
        mutableStateListOf<Float>()
    }
    val size = animateDpAsState(
        targetValue = progress.value,
        tween(
            durationMillis = 1000,
            delayMillis = 100,
            easing = LinearOutSlowInEasing
        )
    )

    if (dotLocationInfoList.isNotEmpty()) {
        with(LocalDensity.current) {
            progress.value = dotLocationInfoList[routeList.indexOf(currentRoute)].toDp() + 9.dp
        }
    }

    SMSTheme { colors, _ ->
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
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
                (1..routeList.size).forEach { _ ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(colors.N20)
                            .onGloballyPositioned {
                                dotLocationInfoList.add(it.positionInParent().x)
                            }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .width(size.value)
                    .height(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(colors.P2)
                    .animateContentSize()
                    .align(Alignment.CenterStart)
            )
        }
    }
}