package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectNameInputComponent(
    projectName: String,
    onValueChange: (String) -> Unit,
) {
    val name = remember {
        mutableStateOf(projectName)
    }

    AddGrayBody1Title(titleText = "이름") {
        SmsTextField(
            setText = name.value,
            placeHolder = "프로젝트 이름입력",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                name.value = it
                onValueChange(it)
            },
            onClickButton = { name.value = "" }
        )
    }
}