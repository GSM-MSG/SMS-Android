package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.component.textfield.NoneIconTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title
import com.sms.presentation.main.ui.detail.data.RelatedLinksData

@Composable
fun ProjectRelatedLinksComponent(
    relatedLinks: List<RelatedLinksData>,
    onLinkNameChanged: (index: Int, value: String) -> Unit,
    onLinkChanged: (index: Int, value: String) -> Unit,
    onAddButton: () -> Unit,
    onClick: (index: Int) -> Unit,
) {
    AddGrayBody1Title(titleText = "관련 링크") {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 640.dp)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(relatedLinks) { index, it ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = it.name,
                            placeHolder = "",
                            onValueChange = { onLinkNameChanged(index, it) }
                        )
                    }
                    Box(modifier = Modifier.weight(2.5f)) {
                        NoneIconTextField(
                            singleLine = true,
                            setChangeText = it.link,
                            placeHolder = "https://github.com",
                            onValueChange = { onLinkChanged(index, it) }
                        )
                    }
                    IconButton(onClick = { onClick(index) }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가", onClick = onAddButton)
            }
        }
    }
}

@Preview
@Composable
private fun ProjectRelatedLinksComponentPre() {
    ProjectRelatedLinksComponent(
        relatedLinks =
        listOf(
            RelatedLinksData(name = "Github", link = "https://github/leehyeonbin.com"),
            RelatedLinksData(name = "Youtube", link = "https://youtube.com")
        ),
        onLinkChanged = { _, _ -> },
        onLinkNameChanged = { _, _ -> },
        onClick = {},
        onAddButton = {}
    )
}