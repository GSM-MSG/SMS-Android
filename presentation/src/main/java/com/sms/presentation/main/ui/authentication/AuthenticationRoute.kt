package com.sms.presentation.main.ui.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sms.presentation.main.viewmodel.AuthenticationViewModel
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.downloader.downloadFile

@Composable
fun AuthenticationRoute(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val authenticationForm = viewModel.authenticationForm.collectAsState()
    val submitAuthenticationState = viewModel.submitAuthenticationFormStatus.collectAsState()
    val isClickable = rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(submitAuthenticationState.value) {
        if (submitAuthenticationState.value is Event.Loading) {
            isClickable.value = false
        } else {
            isClickable.value = true
            if (submitAuthenticationState.value is Event.Success) {

            }
        }
    }

    if (authenticationForm.value != null) {
        AuthenticationScreen(
            authenticationForm = authenticationForm.value!!,
            downloadFile = {
                context.downloadFile(url = it.url, fileName = it.name)
            },
            submitAuthenticationForm = {
                viewModel.submitAuthenticationForm(it)
            },
            onClickBackButton = {},
            isClickAble = isClickable.value
        )
    }
}