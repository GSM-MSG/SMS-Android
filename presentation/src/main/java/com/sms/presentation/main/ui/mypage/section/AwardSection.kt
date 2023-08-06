package com.sms.presentation.main.ui.mypage.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.mypage.component.award.AwardComponent

@Composable
fun AwardSection(awardData: String) {
    AwardComponent(awardData)
}

@Preview
@Composable
private fun AwardSectionPre() {
    AwardSection("수상 1")
}