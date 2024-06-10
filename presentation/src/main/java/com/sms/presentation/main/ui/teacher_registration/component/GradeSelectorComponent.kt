package com.sms.presentation.main.ui.teacher_registration.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun GradeSelectorComponent(
    setGrade: String,
    onClickGradeOpenButton: () -> Unit,
) {
    AddGrayBody1Title(titleText = "학년") {
        SmsBasicTextField(
            trailingIcon = {
                IconButton(onClick = onClickGradeOpenButton) {
                    OpenButtonIcon()
                }
            },
            text = setGrade,
            readOnly = true,
            placeHolder = "학년을 선택해 주세요",
            modifier = Modifier.fillMaxWidth()
        )
    }
}