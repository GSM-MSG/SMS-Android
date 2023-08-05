package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.BlackAddItemButton
import com.sms.presentation.main.ui.mypage.component.award.AwardComponent

@Composable
fun AwardSection(list: List<String>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 50000.dp)) {
        items(list) {
            AwardComponent()
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                BlackAddItemButton(modifier = Modifier.align(Alignment.TopEnd)) {

                }
            }
        }
    }
}

@Preview
@Composable
private fun AwardSectionPre() {
    AwardSection(listOf("1", "2"))
}