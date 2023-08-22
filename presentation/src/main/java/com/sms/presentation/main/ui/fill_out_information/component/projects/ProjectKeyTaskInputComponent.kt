package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectKeyTaskInputComponent(
    projectKeyTask: String,
    onValueChange: (String) -> Unit,
) {
    val keyTask = remember {
        mutableStateOf(projectKeyTask)
    }

    AddGrayBody1Title(titleText = "주요 작업") {
        SmsTextField(
            setText = keyTask.value,
            placeHolder = "주요 작업 내용서술",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                keyTask.value = it
                onValueChange(it)
            },
            onClickButton = { keyTask.value = "" }
        )
    }
}