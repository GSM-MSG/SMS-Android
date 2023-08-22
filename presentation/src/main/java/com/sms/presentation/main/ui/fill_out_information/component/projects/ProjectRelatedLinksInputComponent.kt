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
    val links = remember {
        mutableStateListOf(*relatedLinks.toTypedArray())
    }

    onValueChanged(links)

    AddGrayBody1Title(titleText = "관련 링크") {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 640.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(links) { idx, item ->
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
                            links[idx] = Pair(it, links[idx].second)
                        }
                    }
                    Box(modifier = Modifier.weight(2.5f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = item.second,
                            placeHolder = "https://github.com"
                        ) {
                            links[idx] = Pair(links[idx].first, it)
                        }
                    }
                    IconButton(onClick = { links.removeAt(idx) }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가", onClick = { links.add(Pair("", "")) })
            }
        }
    }
}