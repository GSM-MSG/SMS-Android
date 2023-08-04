package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.CertificationComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun CertificationScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        SmsSpacer()
        CertificationComponent(navController = navController, viewModel = viewModel)
    }
}