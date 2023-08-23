package com.sms.presentation.main.ui.mypage.component.award

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.divider.SmsDivider
import com.sms.presentation.main.ui.mypage.state.ExpandableAwardDate

@Composable
fun AwardComponent(
    awardData: ExpandableAwardDate,
    onNameValueChange: (value: String) -> Unit,
    onTypeValueChange: (value: String) -> Unit,
    onDateValueChange: (value: String) -> Unit,
    onClickCalendar: () -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AwardNameComponent(name = awardData.title, onValueChange = onNameValueChange)
        AwardTypeComponent(type = awardData.organization, onValueChange = onTypeValueChange)
        AwardDateComponent(
            date = awardData.date,
            onValueChange = onDateValueChange,
            onClickIcon = onClickCalendar
        )
        SmsDivider()
    }
}

@Preview
@Composable
private fun AwardComponentPre() {
    AwardComponent(
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