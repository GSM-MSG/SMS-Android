package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData


@Composable
fun AwardScreen() {
    val awardList = remember {
        mutableStateListOf(
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09"),
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09"),
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09")
        )
    }

    LazyColumn {
        itemsIndexed(awardList) { idx, item ->
            AwardComponent(
                data = item,
                saveData = { name, type, date ->
                    awardList[idx] = AwardData(name, type, date)
                },
                onCancelButtonClick = {
                    awardList.removeAt(idx)
                }
            )
        }
    }
}