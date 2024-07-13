package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.segmented_control.SegmentedControl
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.ArrowDownIcon
import com.msg.sms.design.icon.FileIcon
import com.msg.sms.design.icon.XMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.BOOLEAN
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.FILE
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.NUMBER
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.SELECT
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.TEXT
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldValuesModel

@Composable
fun AuthenticationField(
    modifier: Modifier = Modifier,
    fieldType: AuthenticationFieldType,
    values: List<AuthenticationSectionFieldValuesModel>?,
    placeHolder: String?,
    onUpload: () -> Unit,
    scoreDescription: String?,
    onSelect: (values: List<AuthenticationSectionFieldValuesModel>) -> String,
    enteredValue: (enteredValue: String, selectedId: String) -> Unit,
) {
    SMSTheme { _, typography ->
        var value by remember {
            mutableStateOf("")
        }
        var selectedId by remember {
            mutableStateOf("")
        }
        if (fieldType == BOOLEAN) {
            SegmentedControl(
                modifier = modifier.fillMaxWidth(),
                items = values?.map { it.value } ?: listOf(),
                onItemSelection = { index ->
                    selectedId = values?.get(index)?.selectId ?: ""
                    enteredValue(value, selectedId)
                }
            )
        } else {
            SmsBasicTextField(
                modifier = modifier.fillMaxWidth(),
                text = value,
                readOnly = fieldType == SELECT || fieldType == FILE,
                onValueChange = {
                    value = it
                    enteredValue(value, selectedId)
                },
                placeHolder = placeHolder ?: "",
                trailingIcon = {
                    IconButton(onClick = {
                        when (fieldType) {
                            TEXT, NUMBER -> {
                                value = ""
                            }

                            FILE -> onUpload()

                            SELECT -> {
                                selectedId = onSelect(values ?: listOf())
                                enteredValue(value, selectedId)
                            }

                            else -> {}
                        }
                    }) {
                        when (fieldType) {
                            TEXT -> XMarkIcon(modifier = Modifier.size(24.dp))

                            FILE -> FileIcon(modifier = Modifier.size(24.dp))

                            SELECT -> ArrowDownIcon(modifier = Modifier.size(24.dp))

                            else -> {}
                        }
                    }
                },
            )
            if (!scoreDescription.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = scoreDescription,
                    style = typography.caption1,
                    color = Color(0xFFA0ACB1)
                )
            }
        }
    }
}