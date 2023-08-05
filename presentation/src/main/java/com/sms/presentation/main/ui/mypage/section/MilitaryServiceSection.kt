package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.military.MilitaryServiceTypeComponent

@Composable
fun MilitaryServiceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        MilitaryServiceTypeComponent()
    }
}

@Preview
@Composable
private fun MilitaryServiceSectionPre() {
    MilitaryServiceSection()
}