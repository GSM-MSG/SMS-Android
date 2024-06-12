package com.sms.presentation.main.ui.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sms.presentation.main.viewmodel.AuthenticationViewModel

@Composable
fun AuthenticationRoute(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val authenticationForm = viewModel.authenticationForm.collectAsState()

    if (authenticationForm.value != null) {
        AuthenticationScreen(authenticationForm = authenticationForm.value!!) {

        }
    }
}