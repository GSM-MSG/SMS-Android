package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun PositionTypeComponent(
    setPosition: String,
    onClickPositionOpenButton: () -> Unit
){
    AddGrayBody1Title(titleText = "직함") {
        SmsCustomTextField(
            endIcon = { OpenButtonIcon() },
            clickAction = onClickPositionOpenButton,
            setChangeText = setPosition,
            readOnly = true,
            placeHolder = "직함을 선택해 주세요"
        ){
            onClickPositionOpenButton()
        }
    }
}