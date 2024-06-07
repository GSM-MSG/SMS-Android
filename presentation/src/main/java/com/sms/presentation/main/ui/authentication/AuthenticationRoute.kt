package com.sms.presentation.main.ui.authentication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sms.presentation.main.viewmodel.AuthenticationViewModel

@Composable
fun AuthenticationRoute(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    AuthenticationScreen(authenticationForm = viewModel.authenticationForm.value) {
    }
}