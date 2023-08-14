package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.work.WantPayComponent
import com.sms.presentation.main.ui.mypage.component.work.WantWorkFormComponent
import com.sms.presentation.main.ui.mypage.component.work.WorkLocationComponent

@Composable
fun WorkConditionSection(
    wantWorkingAreas: List<String>,
    onValueChange: (index: Int, item: String) -> Unit,
    onClickAddButton: () -> Unit,
    onClickRemoveButton: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        WantWorkFormComponent()
        WantPayComponent()
        WorkLocationComponent(
            workLocationsList = wantWorkingAreas,
            onValueChange = onValueChange,
            onClickAddButton = onClickAddButton,
            onClickRemoveButton = onClickRemoveButton
        )
    }
}

@Preview
@Composable
private fun WorkConditionSectionPre() {
    WorkConditionSection(
        listOf("베이징", "도쿄", "서울"),
        onValueChange = { index, item -> },
        onClickAddButton = {}) {}
}