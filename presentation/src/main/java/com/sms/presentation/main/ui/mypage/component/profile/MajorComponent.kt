package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun MajorComponent() {
    AddGrayBody1Title(titleText = "분야") {
        SmsCustomTextField(
            placeHolder = "FrondEnd",
            modifier = Modifier.fillMaxWidth(),
            endIcon = { OpenButtonIcon() },
//            readOnly = isReadOnly,
            clickAction = {},
            setChangeText = ""
//            setChangeText = if (selectedMajor == "직접입력") enteredMajor else selectedMajor
        ) {
//            enteringMajor(it)
        }
    }
}

@Preview
@Composable
private fun MajorComponentPre() {
    MajorComponent()
}