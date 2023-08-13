package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.SmsChip
import com.msg.sms.design.component.textfield.NoneIconTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectRelatedLinksInputComponent(
    relatedLinks: List<Pair<String, String>>,
    onDeleteButtonClick: (Int) -> Unit,
    onAddButtonClick: () -> Unit,
    onValueChange: (idx: Int, name: String, link: String) -> Unit
) {
    AddGrayBody1Title(titleText = "관련 링크") {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 640.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(relatedLinks) { idx, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = item.first,
                            placeHolder = "Link"
                        ) {
                            onValueChange(idx, it, relatedLinks[idx].second)
                        }
                    }
                    Box(modifier = Modifier.weight(2.5f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = item.second,
                            placeHolder = "https://github.com"
                        ) {
                            onValueChange(idx, relatedLinks[idx].first, it)
                        }
                    }
                    IconButton(onClick = { onDeleteButtonClick(idx) }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가", onClick = onAddButtonClick)
            }
        }
    }
}