package com.sms.presentation.main.ui.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.sms.design.component.topbar.TopNavigation
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.domain.model.authentication.MarkingBoardType
import com.sms.presentation.main.viewmodel.AuthenticationViewModel
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.downloader.downloadFile

@Composable
fun AuthenticationRoute(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val context = LocalContext.current
    val authenticationForm = viewModel.authenticationForm.collectAsState()
    val submitAuthenticationState = viewModel.submitAuthenticationFormStatus.collectAsState()
    val verifyAuthenticationState = viewModel.verifyAuthenticationStatus.collectAsState()
    val verifyAuthenticationData = viewModel.verifyAuthenticationData.collectAsState()

    LaunchedEffect(submitAuthenticationState.value) {
        if (submitAuthenticationState.value is Event.Success) {
            Toast.makeText(context, "인증제 제출이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    Column {
        TopNavigation(
            text = "인증제",
            leftIcon = { BackButtonIcon() },
            onClickLeftButton = onBackPressed
        )
        if (verifyAuthenticationState.value is Event.Success && verifyAuthenticationData.value?.markingBoardType == MarkingBoardType.NOT_SUBMITTED) {
            AuthenticationScreen(
                authenticationForm = authenticationForm.value!!,
                downloadFile = {
                    context.downloadFile(url = it.url, fileName = it.name)
                },
                submitAuthenticationForm = {
                    viewModel.submitAuthenticationForm(it.values.toList())
                },
            )
        } else AuthenticationStatusComponent(verifyAuthenticationModel = verifyAuthenticationData.value)
    }
}