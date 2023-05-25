package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.theme.SMSTheme


@Composable
fun SchoolLifeComponent(
    fileName: String,
    addDreamBook: () -> Unit
) {
    val gsmScore = remember {
        mutableStateOf("")
    }
    val dreamBook = remember {
        mutableStateOf("")
    }
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current

    if (fileName != "")
        dreamBook.value = fileName

    SMSTheme { _, typography ->
        Column(modifier = Modifier.padding(end = 20.dp, start = 20.dp, top = 20.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SmsTitleText(text = "학교 생활", isRequired = true)
                PagerIndicator(
                    indexOfPointingNumber = 1, size = 6, modifier = Modifier.align(
                        Alignment.CenterEnd
                    )
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "인증제 점수", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(
                placeHolder = "인증제 점수 입력",
                modifier = Modifier.fillMaxWidth(),
                setText = gsmScore.value,
                onValueChange = { gsmScore.value = it }) {
                gsmScore.value = ""
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "드림북", style = typography.body2)
            SmsTextField(
                placeHolder = "+ hwp 파일 추가",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            addDreamBook()
                            focusManager.clearFocus()
                        }
                    },
                readOnly = true,
                setText = dreamBook.value,
                onValueChange = { dreamBook.value = it }
            ) {

            }
        }
    }
}