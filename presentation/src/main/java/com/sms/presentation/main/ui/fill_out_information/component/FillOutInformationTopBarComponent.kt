package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.runtime.Composable
import com.msg.sms.design.component.progressbar.FilloutStatusProgressBar
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon

@Composable
fun FillOutInformationTopBarComponent(currentRoute: String, onBackButtonClick: () -> Unit) {
    if (currentRoute != "Search") {
        TopBarComponent(
            text = "정보 입력",
            leftIcon = if (currentRoute != "Profile") {
                { BackButtonIcon() }
            } else null,
            rightIcon = null,
            onClickLeftButton = onBackButtonClick
        )
        FilloutStatusProgressBar(
            routeList = listOf(
                "Profile",
                "SchoolLife",
                "WorkCondition",
                "MilitaryService",
                "Certification",
                "ForeignLanguage",
                "Projects",
                "Award"
            ),
            currentRoute = currentRoute
        )
    }
}