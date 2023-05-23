package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.SchoolLifeComponent

@Composable
fun SchoolLifeScreen(
    navController: NavController,
) {
    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "정보입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

            }
            SmsSpacer()
            SchoolLifeComponent(addDreamBook = {/* TODO(LeeHyeonbin) 드림북 파일 넣는 기능 추가하기 */ })
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
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
                        state = ButtonState.Normal
                    ) {
                        navController.navigate("WorkCondition")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
