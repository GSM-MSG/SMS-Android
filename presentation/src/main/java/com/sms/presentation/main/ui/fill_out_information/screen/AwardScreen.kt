package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun AwardScreen() {
    val name = remember {
        mutableStateOf("")
    }
    val type = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        AddGrayBody1Title(titleText = "이름") {
            SmsTextField(
                modifier = Modifier.fillMaxWidth(),
                setText = name.value,
                placeHolder = "수상 내역 이름 입력",
                onValueChange = { name.value = it }
            ) {
                name.value = ""
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        AddGrayBody1Title(titleText = "종류") {
            SmsTextField(
                modifier = Modifier.fillMaxWidth(),
                setText = type.value,
                placeHolder = "수상 종류입력",
                onValueChange = { type.value = it }
            ) {
                type.value = ""
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        AddGrayBody1Title(titleText = "기간") {
            SmsCustomTextField(
                setChangeText = date.value,
                placeHolder = "yyyy.mm",
                readOnly = true,
                endIcon = { CalendarIcon() },
                clickAction = { /*TODO kimhyunseung: 바텀시트 띄우는 로직 */ }
            )
        }
    }
}