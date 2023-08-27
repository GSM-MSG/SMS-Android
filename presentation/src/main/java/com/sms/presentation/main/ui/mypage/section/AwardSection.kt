package com.sms.presentation.main.ui.mypage.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.mypage.component.award.AwardComponent

@Composable
fun AwardSection(
    awardData: AwardData,
    onNameValueChange: (value: String) -> Unit,
    onTypeValueChange: (value: String) -> Unit,
    onDateValueChange: (value: String) -> Unit,
    onClickCalendar: () -> Unit,
) {
    AwardComponent(
        awardData = awardData,
        onNameValueChange = onNameValueChange,
        onTypeValueChange = onTypeValueChange,
        onDateValueChange = onDateValueChange,
        onClickCalendar = onClickCalendar
    )
}

@Preview
@Composable
private fun AwardSectionPre() {
    AwardSection(
        AwardData(
            title = "수상 1",
            organization = "",
            date = "",
        ),
        onNameValueChange = {},
        onTypeValueChange = {},
        onDateValueChange = {},
        onClickCalendar = {}
    )
}