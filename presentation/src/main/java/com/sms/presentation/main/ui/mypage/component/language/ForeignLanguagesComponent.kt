package com.sms.presentation.main.ui.mypage.component.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.chip.SmsChip
import com.msg.sms.design.component.textfield.NoneIconTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ForeignLanguagesComponent(
    foreignLanguages: List<Pair<String, String>>,
    onValueChangeForeignName: (index: Int, value: String) -> Unit,
    onValueChangeForeignValue: (index: Int, value: String) -> Unit,
    onClickRemoveButton: (index: Int) -> Unit,
    onClickAddButton: () -> Unit,
) {
    AddGrayBody1Title(titleText = "외국어") {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 600.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(foreignLanguages) { index, foreignLanguage ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            NoneIconTextField(
                                modifier = Modifier.fillMaxWidth(0.64f),
                                singleLine = true,
                                placeHolder = "예) 토익",
                                setChangeText = foreignLanguage.first,
                                onValueChange = { onValueChangeForeignName(index, it) }
                            )
                            Box(modifier = Modifier.weight(1f)) {
                                NoneIconTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    placeHolder = "990",
                                    setChangeText = foreignLanguage.second,
                                    onValueChange = { onValueChangeForeignValue(index, it) }
                                )
                            }
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
fun ForeignLanguagesComponentPre() {
    ForeignLanguagesComponent(
        listOf(Pair("토익", "990"), Pair("JLPT", "3급")),
        onValueChangeForeignName = { _, _ -> },
        onValueChangeForeignValue = { _, _ -> },
        onClickRemoveButton = {}) {}
}