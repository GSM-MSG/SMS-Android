package com.sms.presentation.main.ui.fill_out_information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.SchoolLifeComponent

@Preview
@Composable
fun SchoolLifeScreen() {
    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "정보입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

            }
            SmsSpacer()
            SchoolLifeComponent(addDreamBook = {/* TODO(LeeHyeonbin) 드림북 파일 넣는 기능 추가하기 */})
        }
    }
}
