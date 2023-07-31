package com.sms.presentation.main.ui.login.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.R

@Composable
fun TopComponent(context: Context) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            Text(
                text = context.getString(R.string.login_page_topcomponent_text_english),
                style = typography.headline1,
                fontWeight = FontWeight(700),
                color = colors.WHITE,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = context.getString(R.string.login_page_topcomponent_text_korean),
                style = typography.title2,
                fontWeight = FontWeight(600),
                color = colors.N20
            )
        }
    }
}