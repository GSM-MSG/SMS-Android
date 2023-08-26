package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectDescriptionInputComponent(
    projectDescription: String,
    onValueChange: (String) -> Unit
) {
    val description = remember {
        mutableStateOf(projectDescription)
    }

    AddGrayBody1Title(titleText = "프로젝트 설명") {
        SmsTextField(
            setText = description.value,
            placeHolder = "프로젝트 내용서술",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                description.value = it
                onValueChange(it)
            },
            onClickButton = { description.value = "" }
        )
    }
}