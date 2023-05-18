package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.theme.SMSTheme


@Composable
fun SchoolLifeComponent(addDreamBook: () -> Unit) {
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
            SmsTextField(placeHolder = "인증제 점수 입력", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "드림북", style = typography.body2)
            SmsTextField(
                placeHolder = "+ hwp 파일 추가",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        addDreamBook()
                    },
                readOnly = true,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth()) {
                SmsRoundedButton(
                    text = "이전",
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    state = ButtonState.OutLine
                ) {

                }
                Spacer(modifier = Modifier.width(8.dp))
                SmsRoundedButton(
                    text = "다음",
                    modifier = Modifier
                        .weight(4f)
                        .height(48.dp),
                    state = ButtonState.Normal
                ) {

                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}