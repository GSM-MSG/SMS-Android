package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData

@Composable
fun AwardComponent(
    data: AwardData,
    onCancelButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: () -> Unit,
    onNameValueChange: (value: String) -> Unit,
    onTypeValueChange: (value: String) -> Unit,
) {
    val contentVisible = remember {
        mutableStateOf(false)
    }

    ToggleComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        name = data.name.ifEmpty { "수상" },
        contentVisible = contentVisible.value,
        onOpenButtonClick = { contentVisible.value = !contentVisible.value },
        onCancelButtonClick = onCancelButtonClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            AwardNameInputComponent(
                title = "이름",
                placeHolder = "수상 내역 이름 입력",
                text = data.name,
                onButtonClick = { onNameValueChange("") },
                onValueChange = onNameValueChange
            )
            Spacer(modifier = Modifier.height(24.dp))
            AwardNameInputComponent(
                title = "종류",
                placeHolder = "수상 종류입력",
                text = data.type,
                onButtonClick = { onTypeValueChange("") },
                onValueChange = onTypeValueChange
            )
            Spacer(modifier = Modifier.height(24.dp))
            AwardDateBarComponent(date = data.date, onClick = onDateBottomSheetOpenButtonClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}