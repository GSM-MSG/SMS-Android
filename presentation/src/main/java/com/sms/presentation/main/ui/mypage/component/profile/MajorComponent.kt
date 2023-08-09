package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun MajorComponent(majorValue: String, isSelfTyping: Boolean, onClick: () -> Unit, setMajorText: String) {
    val major = remember {
        mutableStateOf(majorValue)
    }
    AddGrayBody1Title(titleText = "분야") {
        SmsCustomTextField(
            placeHolder = "FrondEnd",
            modifier = Modifier.fillMaxWidth(),
            endIcon = { OpenButtonIcon() },
            readOnly = !isSelfTyping,
            clickAction = onClick,
            setChangeText = if(isSelfTyping) major.value else setMajorText
        ) {
            major.value = it
        }
    }
}

@Preview
@Composable
private fun MajorComponentPre() {
    MajorComponent("Android", isSelfTyping = true, onClick = {}, setMajorText = "Android")
}