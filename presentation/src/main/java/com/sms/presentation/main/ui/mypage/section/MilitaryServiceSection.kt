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
fun MilitaryServiceSection(
    setMilitary: String,
    onClickMilitaryOpenButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        MilitaryServiceTypeComponent(
            setMilitary = setMilitary,
            onClickMilitaryOpenButton = onClickMilitaryOpenButton
        )
    }
}

@Preview
@Composable
private fun MilitaryServiceSectionPre() {
    MilitaryServiceSection("병특 희망") {}
}