package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.ListAddButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.fill_out_information.data.AwardRequiredDataInfo

@Composable
fun AwardScreen(
    data: List<AwardData>,
    awardDateMap: Map<Int, String>,
    awardValidationList: List<AwardRequiredDataInfo>,
    onPreviousButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: (index: Int) -> Unit,
    onAddButtonClick: () -> Unit,
    onCancelButtonClick: (index: Int) -> Unit,
    onCompleteButtonClick: () -> Unit,
    onAwardValueChanged: (index: Int, award: AwardData) -> Unit,
) {
    LazyColumn {
        item {
            SmsSpacer()
        }
        itemsIndexed(data) { index, item ->
            onAwardValueChanged(index, item.copy(date = awardDateMap[index] ?: ""))

            AwardComponent(
                data = item,
                isFirstValidationError = awardValidationList.indexOfFirst {
                    it.isNameEmpty || it.isTypeEmpty || it.isDateEmpty
                } == index,
                awardValidation = awardValidationList[index],
                onDateBottomSheetOpenButtonClick = {
                    onDateBottomSheetOpenButtonClick(index)
                },
                onCancelButtonClick = {
                    onCancelButtonClick(index)
                },
                onAwardValueChanged = { award ->
                    onAwardValueChanged(index, award)
                }
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 52.dp),
                horizontalAlignment = Alignment.End
            ) {
                ListAddButton(onClick = onAddButtonClick)
            }
        }
        item {
            AwardBottomButtonComponent(
                onPreviousButtonClick = onPreviousButtonClick,
                onCompleteButtonClick = onCompleteButtonClick
            )
        }
    }
}