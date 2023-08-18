package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.fill_out_information.data.AwardData

@Composable
fun AwardComponent(
    data: AwardData,
    saveData: (name: String, type: String, date: String) -> Unit
) {
    val name = remember {
        mutableStateOf(data.name)
    }
    val type = remember {
        mutableStateOf(data.type)
    }
    val date = remember {
        mutableStateOf(data.date)
    }

    saveData(
        name.value,
        type.value,
        date.value
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        AwardNameInputComponent(
            title = "이름",
            placeHolder = "수상 내역 이름 입력",
            text = name.value,
            onButtonClick = { name.value = "" }
        ) {
            name.value = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        AwardNameInputComponent(
            title = "종류",
            placeHolder = "수상 종류입력",
            text = type.value,
            onButtonClick = { type.value = "" }
        ) {
            type.value = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        AwardDateBarComponent(date = date.value) {
            /*TODO kimhyunseung: 바텀시트 띄우는 로직 */
        }
    }
}