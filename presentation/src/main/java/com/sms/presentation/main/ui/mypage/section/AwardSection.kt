package com.sms.presentation.main.ui.mypage.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.mypage.component.award.AwardComponent
import com.sms.presentation.main.ui.mypage.state.ExpandableAwardDate

@Composable
fun AwardSection(
    awardData: ExpandableAwardDate,
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
        ExpandableAwardDate(
            title = "수상 1",
            organization = "",
            date = "",
            isExpand = true
        ),
        onNameValueChange = {},
        onTypeValueChange = {},
        onDateValueChange = {},
        onClickCalendar = {}
    )
}