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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.BackAndNextButtonComponent
import com.msg.sms.design.component.button.ListAddButton
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.viewmodel.FillOutViewModel


@Composable
fun AwardScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    awardDateMap: Map<Int, String>,
    onDateBottomSheetOpenButtonClick: (idx: Int) -> Unit
) {
    val awardList = remember {
        mutableStateListOf(AwardData("", "", ""))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        itemsIndexed(awardList) { idx, item ->
            awardList[idx] = awardList[idx].copy(date = awardDateMap[idx] ?: "")

            AwardComponent(
                data = item,
                onDateBottomSheetOpenButtonClick = {
                    onDateBottomSheetOpenButtonClick(idx)
                },
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
                onPreviousButtonClick = {},
                onNextButtonClick = {}
            )
        }
    }
}