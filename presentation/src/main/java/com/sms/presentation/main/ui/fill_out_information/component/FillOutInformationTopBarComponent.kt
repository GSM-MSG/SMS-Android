package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.progressbar.FilloutStatusProgressBar
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.Screen

@Composable
fun FillOutInformationTopBarComponent(currentRoute: String, onBackButtonClick: () -> Unit) {
    if (currentRoute != Screen.Search.value) {
        TopBarComponent(
            text = "정보 입력",
            leftIcon = if (currentRoute != Screen.Profile.value) {
                { BackButtonIcon() }
            } else null,
            rightIcon = null,
            onClickLeftButton = onBackButtonClick
        )
        FilloutStatusProgressBar(
            routeList = listOf(
                Screen.Profile.value,
                Screen.SchoolLife.value,
                Screen.WorkCondition.value,
                Screen.MilitaryService.value,
                Screen.Certification.value,
                Screen.ForeignLanguage.value,
                Screen.Projects.value
            ),
            currentRoute = currentRoute
        )
    }
}