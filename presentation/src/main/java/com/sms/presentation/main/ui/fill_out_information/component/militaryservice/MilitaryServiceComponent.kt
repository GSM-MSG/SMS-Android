package com.sms.presentation.main.ui.fill_out_information.component.militaryservice

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun MilitaryServiceComponent(
    selectedMilitaryService: String,
    onMilitaryServiceBottomSheetOpenButtonClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            SmsTitleText(
                text = "병역",
                isRequired = true,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "병특 희망 여부", style = typography.body2, color = colors.N40)
            Spacer(modifier = Modifier.height(8.dp))
            SmsCustomTextField(
                endIcon = { OpenButtonIcon() },
                clickAction = onMilitaryServiceBottomSheetOpenButtonClick,
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "병특 희망",
                setChangeText = selectedMilitaryService,
                readOnly = true
            )
        }
    }
}