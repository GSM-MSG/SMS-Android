package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.msg.sms.design.component.textfield.SmsBasicTextField

@Composable
fun FilterTextFiled(
    value: String,
    modifier: Modifier = Modifier,
    isHopeSalary: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    SmsBasicTextField(
        modifier = modifier,
        text = if (isHopeSalary) "$value 만원" else value,
        onValueChange = {
            onValueChange(it.replace("\\D".toRegex(), ""))
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}