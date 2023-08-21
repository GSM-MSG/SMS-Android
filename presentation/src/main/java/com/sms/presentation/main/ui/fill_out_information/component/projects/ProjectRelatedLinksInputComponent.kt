package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
    onValueChanged: (List<Pair<String, String>>) -> Unit
) {
    val list = remember {
        mutableStateListOf(*relatedLinks.toTypedArray())
    }

    onValueChanged(list)

    AddGrayBody1Title(titleText = "관련 링크") {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 640.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(list) { idx, item ->
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
                            list[idx] = Pair(it, list[idx].second)
                        }
                    }
                    Box(modifier = Modifier.weight(2.5f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = item.second,
                            placeHolder = "https://github.com"
                        ) {
                            list[idx] = Pair(list[idx].first, it)
                        }
                    }
                    IconButton(onClick = { list.removeAt(idx) }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가", onClick = { list.add(Pair("", "")) })
            }
        }
    }
}