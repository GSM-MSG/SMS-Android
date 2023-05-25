package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.CertificationComponent
import com.sms.presentation.main.viewmodel.StudentViewModel

@Composable
fun CertificationScreen(
    navController: NavController,
    viewModel: StudentViewModel
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)) {
        TopBarComponent(text = "정보기입", leftIcon = { BackButtonIcon() }, rightIcon = null) {

        }
        SmsSpacer()
        CertificationComponent(navController = navController)
    }
}