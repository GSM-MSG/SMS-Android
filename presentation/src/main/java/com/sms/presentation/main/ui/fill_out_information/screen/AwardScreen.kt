package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.BackAndNextButtonComponent
import com.msg.sms.design.component.button.ListAddButton
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData


@Composable
fun AwardScreen(
    onDateBottomSheetOpenButtonClick: () -> Unit
) {
    val awardList = remember {
        mutableStateListOf(
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09"),
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09"),
            AwardData("코뿔소상", "코가 큰남자에게 주는상", "2023.09")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        itemsIndexed(awardList) { idx, item ->
            AwardComponent(
                data = item,
                onDateBottomSheetOpenButtonClick = onDateBottomSheetOpenButtonClick,
                onNameValueChange = { awardList[idx] = awardList[idx].copy(name = it) },
                onTypeValueChange = { awardList[idx] = awardList[idx].copy(type = it) },
                onCancelButtonClick = {
                    awardList.removeAt(idx)
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            ListAddButton {
                awardList.add(AwardData("", "", ""))
            }
        }
        item {
            BackAndNextButtonComponent(
                onPreviousButtonClick = { /*TODO*/ },
                onNextButtonClick = {}
            )
        }
    }
}

@Preview
@Composable
fun AwardScreenPre() {
    AwardScreen {

    }
}