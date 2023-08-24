package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
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
    ToggleComponent(name = data.name.ifEmpty { "수상" }, onCancelButtonClick = onCancelButtonClick) {
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
        }
    }
}