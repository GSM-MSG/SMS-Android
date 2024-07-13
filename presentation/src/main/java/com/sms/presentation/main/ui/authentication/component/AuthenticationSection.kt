package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title
import com.msg.sms.domain.model.authentication.request.AtomicAuthenticationFieldModel
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionGroupModel

@Composable
fun AuthenticationSection(
    modifier: Modifier = Modifier,
    sectionName: String,
    plusminusVisible: Boolean,
    groups: List<AuthenticationSectionGroupModel>,
    onUpload: () -> Unit = {},
    removeFieldGroup: (groupIndex: Int, uuids: List<String>) -> Unit = { _, _ -> },
    onValueChanged: (uuid: String, data: AtomicAuthenticationFieldModel) -> Unit,
) {
    AddGrayBody1Title(modifier = modifier, titleText = sectionName) {
        val currentFieldCount = rememberSaveable {
            mutableStateOf(1)
        }
        // section
        LazyColumn(
            modifier = Modifier.heightIn(max = 5000.dp)
        ) {
            items(currentFieldCount.value) { groupIndex ->
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
                                    enteredValue = { enteredValue, selectedId ->
                                        onValueChanged(
                                            item.uuid + groupIndex,
                                            AtomicAuthenticationFieldModel(
                                                fieldId = item.fieldId,
                                                value = enteredValue,
                                                selectId = selectedId,
                                                fieldType = item.fieldType,
                                                groupId = group.groupId,
                                                groupIndex = groupIndex
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                    if (plusminusVisible) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SmsChip(text = "추가", onClick = { currentFieldCount.value += 1 })
                                IconButton(onClick = {
                                    if (currentFieldCount.value > 1) {
                                        currentFieldCount.value -= 1
                                        removeFieldGroup(
                                            groupIndex,
                                            groups.first().fields.map { it.uuid }
                                        )
                                    }
                                }) {
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
