package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsCheckBox
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.CalendarIcon
import com.msg.sms.design.icon.FlowIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.util.hideKeyboard

@Composable
fun ProjectScheduleInputComponent(
    startDateText: String,
    endDateText: String,
    isProjectProgress: Boolean,
    onStartDateCalendarClick: () -> Unit,
    onEndDateCalendarClick: () -> Unit,
    onProgressButtonClick: () -> Unit
) {
    val context = LocalContext.current as FillOutInformationActivity

    AddGrayBody1Title(titleText = "진행 기간") {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
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
                        clickAction = {
                            context.hideKeyboard()
                            onStartDateCalendarClick()
                        },
                        setChangeText = startDateText,
                        placeHolder = "yyyy.mm",
                        readOnly = true
                    )
                }
                if (!isProjectProgress) {
                    FlowIcon()
                    Box(modifier = Modifier.weight(1f)) {
                        SmsCustomTextField(
                            endIcon = { CalendarIcon() },
                            clickAction = {
                                context.hideKeyboard()
                                onEndDateCalendarClick()
                            },
                            setChangeText = endDateText,
                            placeHolder = "yyyy.mm",
                            readOnly = true
                        )
                    }
                }
            }
            SMSTheme { colors, typography ->
                Text(
                    text = "프로젝트 진행 기간을 입력해 주세요.",
                    style = typography.caption1,
                    color = colors.ERROR,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SmsCheckBox(checked = isProjectProgress, onClick = onProgressButtonClick)
                SMSTheme { colors, typography ->
                    Text(
                        text = "진행 중",
                        style = typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = colors.N30
                    )
                }
            }
        }
    }
}