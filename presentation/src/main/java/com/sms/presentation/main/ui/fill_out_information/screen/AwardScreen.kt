package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ListAddButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.viewmodel.FillOutViewModel


@Composable
fun AwardScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    awardDateMap: Map<Int, String>,
    onPreviousButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: (idx: Int) -> Unit
) {
    val awardList = remember {
        mutableStateListOf(AwardData("", "", ""))
    }

    LazyColumn {
        item {
            SmsSpacer()
        }
        itemsIndexed(awardList) { idx, item ->
            awardList[idx] = awardList[idx].copy(date = awardDateMap[idx] ?: "")

            AwardComponent(
                data = item,
                onDateBottomSheetOpenButtonClick = {
                    onDateBottomSheetOpenButtonClick(idx)
                },
                onNameValueChange = {
                    awardList[idx] = awardList[idx].copy(name = it)
                },
                onTypeValueChange = {
                    awardList[idx] = awardList[idx].copy(type = it)
                },
                onCancelButtonClick = {
                    awardList.removeAt(idx)
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
                ListAddButton {
                    awardList.add(AwardData("", "", ""))
                }
            }
        }
        item {
            AwardBottomButtonComponent(
                onPreviousButtonClick = onPreviousButtonClick,
                onCompleteButtonClick = {
                    /* TODO kimhyunseung : 데이터 모아서 정보기입 요청 보내기 */
                }
            )
        }
    }
}