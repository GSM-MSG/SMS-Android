package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardDateComponent(
    date: String,
    onValueChange: (value: String) -> Unit,
    onClickIcon: () -> Unit,
) {
    AddGrayBody1Title(titleText = "기간") {
        SmsBasicTextField(
            trailingIcon = {
                IconButton(onClick = onClickIcon) {
                    CalendarIcon()
                }
            },
            text = date,
            placeHolder = "2023.09",
            readOnly = true,
            onValueChange = { onValueChange(it) },
        )
    }
}

@Preview
@Composable
private fun AwardDateComponentPre() {
    AwardDateComponent(date = "2023. 03. 02", onValueChange = {}, onClickIcon = {})
}