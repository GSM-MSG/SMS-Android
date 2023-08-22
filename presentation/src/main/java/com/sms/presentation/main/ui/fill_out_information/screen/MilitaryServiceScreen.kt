package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.militaryservice.MilitaryServiceComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MilitaryServiceScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit
) {
    val selectedMilitaryService = remember {
        mutableStateOf("")
    }
    val data = viewModel.getEnteredMilitaryServiceInformation()
    val militaryServiceServiceList = listOf("병특 희망", "희망하지 않음", "상관없음", "해당 사항 없음")

    bottomSheetContent(
        content = {
            SelectorBottomSheet(
                list = militaryServiceServiceList,
                bottomSheetState = bottomSheetState,
                selected = if (selectedMilitaryService.value == "") data.militaryService else selectedMilitaryService.value,
                itemChange = { selectedMilitaryService.value = it }
            )
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SmsSpacer()
        MilitaryServiceComponent(
            bottomSheetState = bottomSheetState,
            selectedMilitaryService = if (selectedMilitaryService.value == "") data.militaryService else selectedMilitaryService.value
        )
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth()) {
                SmsRoundedButton(
                    text = "이전",
                    modifier = Modifier
                        .weight(2f)
                        .height(48.dp),
                    state = ButtonState.OutLine
                ) {
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.width(8.dp))
                SmsRoundedButton(
                    text = "다음",
                    modifier = Modifier
                        .weight(4f)
                        .height(48.dp),
                    state = ButtonState.Normal,
                    enabled = selectedMilitaryService.value != "" || (selectedMilitaryService.value == "" && data.militaryService != "")
                ) {
                    viewModel.setEnteredMilitaryServiceInformation(if (selectedMilitaryService.value == "") data.militaryService else selectedMilitaryService.value)
                    navController.navigate("Certification")
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}