package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ProfileComponent() {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = "프로필",
                        style = typography.title1,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "*",
                        style = typography.title1,
                        fontWeight = FontWeight.Bold,
                        color = colors.S2
                    )
                }
                PagerIndicator(
                    modifier = Modifier.align(
                        Alignment.CenterEnd
                    ),
                    indexOfPointingNumber = 0,
                    size = 6
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileComponentPre() {
    ProfileComponent()
}