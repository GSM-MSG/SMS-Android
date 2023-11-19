package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.progressbar.FilloutStatusProgressBar
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.FillOutPage

@Composable
fun FillOutInformationTopBarComponent(
    currentRoute: String,
    onBackButtonClick: () -> Unit
) {
    if (currentRoute != FillOutPage.Search.value) {
        TopBarComponent(
            text = "정보 입력",
            leftIcon = if (currentRoute != FillOutPage.Profile.value) {
                { BackButtonIcon() }
            } else null,
            rightIcon = null,
            onClickLeftButton = onBackButtonClick
        )
    }
}