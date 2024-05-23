package com.sms.presentation.main.ui.mypage.component.work

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun WantWorkFormComponent(
    setText: String,
    onClickOpenButton: () -> Unit,
) {
    AddGrayBody1Title(titleText = "희망 고용 형태") {
        SmsCustomTextField(
            trailingIcon = {
                IconButton(onClick = onClickOpenButton) {
                    OpenButtonIcon()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            text = setText,
            readOnly = true,
            placeHolder = "내가 뭐가 될 상인가?"
        )
    }
}

@Preview
@Composable
private fun WantWorkFormComponentPre() {
    WantWorkFormComponent("정규직", {})
}