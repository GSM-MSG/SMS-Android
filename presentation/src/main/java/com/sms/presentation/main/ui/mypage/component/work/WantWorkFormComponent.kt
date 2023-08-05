package com.sms.presentation.main.ui.mypage.component.work

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun WantWorkFormComponent() {
    AddGrayBody1Title(titleText = "희망 고용 형태") {
        SmsCustomTextField(
            modifier = Modifier.fillMaxWidth(),
            endIcon = { OpenButtonIcon() },
            clickAction = { /*TODO(Leehyeonbin): 바텀 시트 띄우는거 구현해야지*/ },
            setChangeText = "",
            placeHolder = "내가 뭐가 될 상인가?"
        )
    }
}

@Preview
@Composable
private fun WantWorkFormComponentPre() {
    WantWorkFormComponent()
}