package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.language.ForeignLanguagesComponent

@Composable
fun ForeignLanguagesSection(
    foreignLanguages: List<Pair<String, String>>,
    onValueChangeForeignValue: (index: Int, value: String) -> Unit,
    onValueChangeForeignName: (index: Int, value: String) -> Unit,
    onClickRemoveButton: (index: Int) -> Unit,
    onClickAddButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 20.dp)
    ) {
        ForeignLanguagesComponent(
            foreignLanguages = foreignLanguages,
            onValueChangeForeignValue = onValueChangeForeignValue,
            onValueChangeForeignName = onValueChangeForeignName,
            onClickRemoveButton = onClickRemoveButton,
            onClickAddButton = onClickAddButton
        )
    }
}

@Preview
@Composable
private fun ForeignLanguagesSectionPre() {
    ForeignLanguagesSection(
        listOf(Pair("한국어", "원어민"), Pair("토익", "990")),
        onValueChangeForeignName = { _, _ -> },
        onValueChangeForeignValue = { _, _ -> },
        onClickRemoveButton = {},
        onClickAddButton = {}
    )
}