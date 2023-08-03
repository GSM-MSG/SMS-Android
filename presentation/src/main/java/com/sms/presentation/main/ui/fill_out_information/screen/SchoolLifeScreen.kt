package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.SchoolLifeComponent
import com.sms.presentation.main.ui.util.textFieldChecker
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun SchoolLifeScreen(
    navController: NavController,
    viewModel: FillOutViewModel
) {
    val data = viewModel.getEnteredSchoolLifeInformation()

    val gsmAuthenticationScore = remember {
        mutableStateOf(data.gsmAuthenticationScore)
    }

    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            SmsSpacer()
            SchoolLifeComponent(
                enteredGsmAuthenticationScore = gsmAuthenticationScore.value,
                gsmAuthenticationScore = {
                    gsmAuthenticationScore.value = it
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
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
                        enabled = textFieldChecker(gsmAuthenticationScore.value),
                        state = ButtonState.Normal
                    ) {
                        viewModel.setEnteredSchoolLifeInformation(
                            gsmAuthenticationScore = gsmAuthenticationScore.value,
                        )
                        navController.navigate("WorkCondition")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
