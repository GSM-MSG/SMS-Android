package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.selector.SmsSelectionControls
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FilterSelectionControls(
    title: String,
    firstSelectionName: String,
    secondSelectionName: String,
    selectionValue: Boolean,
    onSelectionClick: (Boolean) -> Unit
) {
    SMSTheme { colors, typography ->
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                color = colors.BLACK
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(0.5f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SmsSelectionControls(selected = selectionValue) {
                            onSelectionClick(true)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = firstSelectionName,
                            style = typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = colors.N50
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxWidth(0.5f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SmsSelectionControls(selected = !selectionValue) {
                            onSelectionClick(false)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = secondSelectionName,
                            style = typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = colors.N50
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterSelectionControls() {
    val testValue = remember {
        mutableStateOf(true)
    }

    FilterSelectionControls(
        title = "희망 연봉",
        firstSelectionName = "오름차순",
        secondSelectionName = "내림차순",
        selectionValue = testValue.value,
        onSelectionClick = {
            testValue.value = it
        }
    )
}