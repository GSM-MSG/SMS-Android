package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.militaryservice.MilitaryServiceComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun MilitaryServiceScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    selectedMilitaryService: String,
    onMilitaryServiceBottomSheetOpenButtonClick: () -> Unit
) {
    val data = viewModel.getEnteredMilitaryServiceInformation()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SmsSpacer()
        MilitaryServiceComponent(
            selectedMilitaryService = selectedMilitaryService,
            onMilitaryServiceBottomSheetOpenButtonClick = onMilitaryServiceBottomSheetOpenButtonClick
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
                    enabled = selectedMilitaryService != "" || data.militaryService != ""
                ) {
                    viewModel.setEnteredMilitaryServiceInformation(if (selectedMilitaryService == "") data.militaryService else selectedMilitaryService)
                    navController.navigate("Certification")
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}