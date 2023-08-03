package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.theme.SMSTheme


@Composable
fun SchoolLifeComponent(
    enteredGsmAuthenticationScore: String,
    gsmAuthenticationScore: (String) -> Unit,
) {
    SMSTheme { _, typography ->
        Column(modifier = Modifier.padding(end = 20.dp, start = 20.dp, top = 20.dp)) {
            SmsTitleText(text = "학교 생활", isRequired = true)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "인증제 점수", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(
                placeHolder = "인증제 점수 입력",
                modifier = Modifier.fillMaxWidth(),
                setText = enteredGsmAuthenticationScore,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = {
                    gsmAuthenticationScore(it)
                }) {
                gsmAuthenticationScore("")
            }
        }
    }
}