package com.sms.presentation.main.ui.mypage.component.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.SmsChip
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun WorkLocationComponent(workLocationsList: List<String>) {
    AddGrayBody1Title(titleText = "근무지역") {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 2000.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workLocationsList) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        SmsTextField(
                            setText = it,
                            modifier = Modifier.fillMaxWidth(),
                            placeHolder = "니가가라 하와이"
                        ) {

                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가") {

                }
            }
        }
    }
}

@Preview
@Composable
private fun WorkLocationComponentPre() {
    WorkLocationComponent(listOf("도쿄", "광저우", "교토"))
}