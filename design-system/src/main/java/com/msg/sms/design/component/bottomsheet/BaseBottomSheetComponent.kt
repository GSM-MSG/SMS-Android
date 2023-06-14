package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.color.LightColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseBottomSheetComponent(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    text: String,
    textStyle: TextStyle = TextStyle(),
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    leftIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        backgroundColor = LightColor.N10
                        this.awaitRelease()
                        backgroundColor = Color.Transparent
                        onClick()
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    },
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon()
            Text(
                text = text,
                style = textStyle,
                color = textColor,
                fontWeight = fontWeight
            )
        }
    }
}