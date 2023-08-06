package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardNameComponent(name: String) {
    val awardName = remember {
        mutableStateOf(name)
    }
    AddGrayBody1Title(titleText = "이름") {
        SmsTextField(
            modifier = Modifier.fillMaxWidth(),
            setText = awardName.value,
            placeHolder = "제 19회 스마틴 앱 챌린지 대상",
            onValueChange = { awardName.value = it }) {
            awardName.value = ""
        }
    }
}

@Preview
@Composable
private fun AwardNameComponentPre() {
    AwardNameComponent(name = "제 19회 스마틴 앱 챌린지 대상")

}