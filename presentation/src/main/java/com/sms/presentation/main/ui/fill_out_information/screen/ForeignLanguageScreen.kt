package com.sms.presentation.main.ui.fill_out_information.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.foreignlanguage.ForeignLanguageComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ForeignLanguageScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    lifecycleScope: CoroutineScope,
) {
    Column(modifier = Modifier.background(Color.White)) {
        SmsSpacer()
        ForeignLanguageComponent(
            navController = navController,
            viewModel = viewModel,
            lifecycleScope = lifecycleScope,
        )
    }
}