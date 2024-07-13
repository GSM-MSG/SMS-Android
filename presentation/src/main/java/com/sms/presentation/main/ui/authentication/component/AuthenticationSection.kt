package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title
import com.msg.sms.domain.model.authentication.request.AtomicAuthenticationFieldModel
import com.msg.sms.domain.model.authentication.request.AuthenticationFieldModel
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldValuesModel
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionGroupModel

@Composable
fun AuthenticationSection(
    modifier: Modifier = Modifier,
    sectionName: String,
    maxCount: Int,
    currentFieldCount: Int = 1,
    groups: List<AuthenticationSectionGroupModel>,
    onUpload: () -> Unit = {},
    onSelect: (values: List<AuthenticationSectionFieldValuesModel>) -> String = { _ -> "" },
    addField: (index: Int) -> Unit = {},
    removeField: (index: Int) -> Unit = {},
    onValueChanged: (uuid: String, data: AtomicAuthenticationFieldModel) -> Unit,
) {
    AddGrayBody1Title(modifier = modifier, titleText = sectionName) {
        // section
        LazyColumn(
            modifier = Modifier.heightIn(max = 5000.dp)
        ) {
            items(currentFieldCount) {
                // group
                LazyColumn(modifier = Modifier.heightIn(max = 1000.dp)) {
                    items(groups) { group ->
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.heightIn(max = 1000.dp)
                        ) {
                            itemsIndexed(group.fields) { _, item ->
                                AuthenticationField(
                                    fieldType = item.fieldType,
                                    values = item.values,
                                    placeHolder = item.placeholder,
                                    scoreDescription = item.scoreDescription,
                                    onUpload = onUpload,
                                    onSelect = onSelect,
                                    enteredValue = { enteredValue, selectedId ->
                                        onValueChanged(
                                            item.uuid,
                                            AtomicAuthenticationFieldModel(
                                                fieldId = item.fieldId,
                                                value = enteredValue,
                                                selectId = selectedId,
                                                fieldType = item.fieldType,
                                                groupId = group.groupId
                                            )
                                        )
                                    }
                                )
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
