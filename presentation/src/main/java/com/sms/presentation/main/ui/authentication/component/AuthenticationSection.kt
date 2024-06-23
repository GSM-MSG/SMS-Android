package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title
import com.msg.sms.domain.model.authentication.request.AuthenticationObject
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldModel
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldValuesModel

@Composable
fun AuthenticationSection(
    modifier: Modifier = Modifier,
    section: String,
    maxCount: Int,
    currentFieldCount: Int = 1,
    fields: List<AuthenticationSectionFieldModel>,
    onUpload: () -> Unit = {},
    onSelect: (values: List<AuthenticationSectionFieldValuesModel>) -> String = { _ -> "" },
    addField: (index: Int) -> Unit = {},
    removeField: (index: Int) -> Unit = {},
    onValueChanged: (List<AuthenticationObject>) -> Unit,
) {
    val sectionItem = rememberSaveable {
        mutableListOf<AuthenticationObject>()
    }
    AddGrayBody1Title(modifier = modifier, titleText = section) {
        LazyColumn(
            modifier = Modifier.heightIn(max = 5000.dp)
        ) {
            items(currentFieldCount) {
                LazyColumn(modifier = Modifier.heightIn(max = 1000.dp)) {
                    itemsIndexed(fields) { index, item ->
                        AuthenticationField(
                            modifier = Modifier.padding(bottom = if (index != fields.lastIndex) 8.dp else 0.dp),
                            fieldType = item.fieldType,
                            values = item.values,
                            example = item.example,
                            scoreDescription = item.scoreDescription,
                            onUpload = onUpload,
                            onSelect = onSelect,
                            enteredValue = { enteredValue, selectedId ->
                                sectionItem[index] = AuthenticationObject(
                                    fieldId = item.fieldId,
                                    value = enteredValue,
                                    selectId = selectedId,
                                    fieldType = item.fieldType
                                )
                                onValueChanged(sectionItem)
                            }
                        )
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


@Preview
@Composable
private fun AuthenticationSectionPreview() {
    AuthenticationSection(
        section = "활동 제목",
        maxCount = 3,
        fields = listOf(
            AuthenticationSectionFieldModel(
                fieldId = "",
                fieldType = AuthenticationFieldType.TEXT,
                values = null,
                example = "TEXT",
                scoreDescription = ""

            ),
            AuthenticationSectionFieldModel(
                fieldId = "",
                fieldType = AuthenticationFieldType.FILE,
                values = null,
                example = "TEXT",
                scoreDescription = ""
            )
        ),
        onValueChanged = {},
    )
}
