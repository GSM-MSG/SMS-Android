package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun GradeSelectorComponent(
    setGrade: String,
    onClickGradeOpenButton: () -> Unit
){
    AddGrayBody1Title(titleText = "학년") {
        SmsCustomTextField(
            endIcon = { OpenButtonIcon() },
            clickAction = onClickGradeOpenButton,
            setChangeText = setGrade,
            readOnly = true,
            placeHolder = "학년을 선택해 주세요")
    }
}