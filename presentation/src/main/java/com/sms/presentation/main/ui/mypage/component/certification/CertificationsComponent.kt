package com.sms.presentation.main.ui.mypage.component.certification

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
fun CertificationsComponent(certificationList: List<String>) {
    AddGrayBody1Title(titleText = "자격증") {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 900.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(certificationList) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        SmsTextField(
                            setText = it,
                            modifier = Modifier.fillMaxWidth()
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
private fun CertificationsComponentPre() {
    CertificationsComponent(listOf("정보처리 산업기사"))
}