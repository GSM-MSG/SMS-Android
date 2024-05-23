package com.sms.presentation.main.ui.mypage.component.life

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SchoolScoreComponent(score: String, onValueChange: (value: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    AddGrayBody1Title(titleText = "인증제 점수") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            text = score,
            placeHolder = "몇 점이고! 인증제 점수말이다.",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            onValueChange = { onValueChange(if (it == "") "0" else it) }
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun SchoolScoreComponentPre() {
    SchoolScoreComponent(score = "800") {}
}