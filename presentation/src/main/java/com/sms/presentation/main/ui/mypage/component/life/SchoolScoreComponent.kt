package com.sms.presentation.main.ui.mypage.component.life

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun SchoolScoreComponent(score: Int) {
    val gsmScore = remember {
        mutableStateOf(score)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    AddGrayBody1Title(titleText = "인증제 점수") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = gsmScore.value.toString(),
            placeHolder = "몇 점이고! 인증제 점수말이다.",
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            onValueChange = { gsmScore.value = it.toInt() }
        ) {
            gsmScore.value = 0
        }
    }
}

@Preview
@Composable
private fun SchoolScoreComponentPre() {
    SchoolScoreComponent(score = 800)
}