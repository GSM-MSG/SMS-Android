package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsCheckBox
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.icon.FlowIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectScheduleComponent() {
    AddGrayBody1Title(titleText = "진행 기간") {
        val isProgress = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    SmsCustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        endIcon = { CalendarIcon() },
                        clickAction = {},
                        setChangeText = "",
                        placeHolder = "2001.06"
                    )
                }
                if (!isProgress.value) {
                    FlowIcon()
                    Box(modifier = Modifier.weight(1f)) {
                        SmsCustomTextField(
                            endIcon = { CalendarIcon() },
                            clickAction = {},
                            setChangeText = "",
                            placeHolder = "2020.03"
                        )
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SmsCheckBox(checked = isProgress.value) {
                    isProgress.value = !isProgress.value
                }
                SMSTheme { colors, typography ->
                    Text(text = "진행 중", style = typography.body1, fontWeight = FontWeight.Normal, color = colors.N30)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProjectScheduleComponentPre() {
    ProjectScheduleComponent()
}