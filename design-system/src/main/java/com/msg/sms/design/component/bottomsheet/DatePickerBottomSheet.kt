package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatePickerBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    onDateValueChanged: (date: String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val year = remember {
        mutableStateOf("")
    }
    val month = remember {
        mutableStateOf("")
    }

    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier.padding(end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {
                Text(
                    text = "날짜 선택",
                    style = typography.title2,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "완료",
                    style = typography.body2,
                    color = colors.P2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .smsClickable {
                            scope.launch { bottomSheetState.hide() }
                            onDateValueChanged("${year.value}.${month.value}")
                        }
                )
            }
            SmsDatePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(163.dp)
                    .padding(horizontal = 30.dp),
                onYearValueChange = { year.value = it },
                onMonthValueChange = { month.value = it }
            )
        }
    }
}