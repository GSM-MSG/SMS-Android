package com.sms.presentation.main.ui.mypage.component.certification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.component.textfield.SmsOnlyInputTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun CertificationsComponent(
    certifications: List<String>,
    onValueChange: (index: Int, value: String) -> Unit,
    onClickRemoveButton: (index: Int) -> Unit,
    onClickAddButton: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        if (certifications.isEmpty()) onClickAddButton()
    }

    AddGrayBody1Title(titleText = "자격증") {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 900.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(certifications) { index, certification ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        SmsOnlyInputTextField(
                            text = certification,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { onValueChange(index, it) },
                            placeHolder = "정보처리 산업기사"
                        ) {
                            onValueChange(index, "")
                        }
                    }
                    IconButton(onClick = { onClickRemoveButton(index) }) {
                        TrashCanIcon()
                    }
                }
            }
            item {
                SmsChip(text = "추가", onClick = onClickAddButton)
            }
        }
    }
}

@Preview
@Composable
private fun CertificationsComponentPre() {
    CertificationsComponent(
        listOf("정보처리 산업기사"),
        onValueChange = { _, _ -> },
        onClickRemoveButton = {},
        onClickAddButton = {})
}