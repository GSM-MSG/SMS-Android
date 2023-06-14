package com.sms.presentation.main.ui.login.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun ShowTheTermsOfUse(context: Context) {
    SMSTheme { colors, typography ->
        Text(
            text = "약관 보기",
            color = colors.WHITE,
            style = typography.body2,
            modifier = Modifier.smsClickable {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://matsougeum.notion.site/fadfc4fb783947d8a72e1c7b46b8c8f6?pvs=4")
                )
                context.startActivity(urlIntent)
            },
            textDecoration = TextDecoration.Underline
        )
    }
}