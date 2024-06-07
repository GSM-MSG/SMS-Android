package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.component.segmented_control.SegmentedControl
import com.msg.sms.design.icon.ArrowDownIcon
import com.msg.sms.design.icon.FileIcon
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.icon.XMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title
import com.msg.sms.domain.model.authentication.AuthenticationFormModel

@Composable
fun AuthenticationSection(
    modifier: Modifier = Modifier,
    section: String,
    description: String? = null,
    maxCount: Int,
    currentFieldCount: Int = 1,
    fields: List<AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSectionFieldModel>,
    onClickButton: (Int) -> Unit,
    addField: (index: Int) -> Unit = {},
    removeField: (index: Int) -> Unit = {},
    onValueChanged: (Int, String) -> Unit,
) {
    SMSTheme { colors, typography ->
        AddGrayBody1Title(modifier = modifier, titleText = section) {
            LazyColumn(
                modifier = Modifier.heightIn(max = 5000.dp)
            ) {
                items(currentFieldCount) {
                    LazyColumn(modifier = Modifier.heightIn(max = 1000.dp)) {
                        items(currentFieldCount) {
                            LazyColumn(modifier = Modifier.heightIn(max = 1000.dp)) {
                                itemsIndexed(fields) { index, item ->
                                    if (item.type == AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.BOOLEAN) {
                                        SegmentedControl(items = item.values?.map { it.value }
                                            ?: listOf()) {
                                            onValueChanged(index, item.values?.map { it.selectId }
                                                ?.get(it) ?: "")
                                        }
                                    } else {
                                        OutlinedTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = item.key,
                                            onValueChange = { value ->
                                                onValueChanged(index, value)
                                            },
                                            placeholder = {
                                                Text(
                                                    text = item.example ?: "",
                                                    style = typography.body1
                                                )
                                            },
                                            textStyle = typography.body1,
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                backgroundColor = colors.N10,
                                                placeholderColor = colors.N30,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = colors.P2
                                            ),
                                            trailingIcon = {
                                                IconButton(onClick = { onClickButton(index) }) {
                                                    when (item.type) {
                                                        AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.TEXT -> XMarkIcon(
                                                            modifier = Modifier.size(
                                                                24.dp
                                                            )
                                                        )

                                                        AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.FILE -> FileIcon(
                                                            modifier = Modifier.size(24.dp)
                                                        )

                                                        AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.SELECT_VALUE -> ArrowDownIcon(
                                                            modifier = Modifier.size(
                                                                24.dp
                                                            )
                                                        )

                                                        else -> {}
                                                    }
                                                }
                                            },
                                        )
                                        if (description != null) {
                                            Spacer(
                                                modifier = Modifier.height(8.dp)
                                            )
                                            Text(
                                                text = description,
                                                style = typography.caption1,
                                                color = Color(0xFFA0ACB1)
                                            )
                                        }
                                        if (index != fields.size - 1) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                }
                                if (maxCount > 1) {
                                    item {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            SmsChip(text = "추가", onClick = { addField(it) })
                                            IconButton(onClick = { removeField(it) }) {
                                                TrashCanIcon(modifier = Modifier.size(24.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun AuthenticationSectionPreview() {
    AuthenticationSection(
        section = "활동 제목",
        description = "활동 제목 DESCRIPTION",
        maxCount = 3,
        fields = listOf(
            AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSectionFieldModel(
                key = "String",
                type = AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.TEXT,
                values = null,
                example = "TEXT"
            ),
            AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSectionFieldModel(
                key = "String",
                type = AuthenticationFormModel.AuthenticationSectionModel.AuthenticationSelectionType.FILE,
                values = null,
                example = "TEXT"
            )
        ),
        onValueChanged = { _, _ -> },
        onClickButton = {},
    )
}
