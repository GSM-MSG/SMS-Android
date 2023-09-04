package com.sms.presentation.main.ui.mypage.component.work

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WantPayComponent(wantPay: String, onValueChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    AddGrayBody1Title(titleText = "희망 연봉") {
        SmsTextField(
            setText = wantPay,
            modifier = Modifier.fillMaxWidth(),
            placeHolder = "지금 내 통장엔 억억억억억억",
            onValueChange = {
                if (it == "") {
                    onValueChange("0")
                } else if (it.length <= 4) {
                    runCatching {
                        it.toInt()
                    }.onSuccess { _ ->
                        onValueChange(it.trim())
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
        ) {
            onValueChange("0")
        }
        Spacer(modifier = Modifier.height(4.dp))
        SMSTheme { colors, typography ->
            Text(
                text = if (wantPay == "0") "상관없음" else if (wantPay == "") "" else "${wantPay.toInt()}만원",
                color = colors.N30,
                style = typography.body2
            )
        }
    }
}

@Preview
@Composable
private fun WantPayComponentPre() {
    WantPayComponent("2000") {}
}