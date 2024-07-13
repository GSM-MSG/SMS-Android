package com.sms.presentation.main.ui.authentication

import android.webkit.MimeTypeMap
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
import java.util.UUID

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
        } else if (submitAuthenticationState.value == Event.BadRequest) {
            Toast.makeText(
                context,
                "지원하지 않는 파일 형식입니다. hwp, hwpx, pdf 형식만 업로드 가능합니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column {
        TopNavigation(
            text = "인증제",
            leftIcon = { BackButtonIcon() },
            onClickLeftButton = onBackPressed
        )
        if (verifyAuthenticationState.value is Event.Success && verifyAuthenticationData.value?.markingBoardType == MarkingBoardType.NOT_SUBMITTED && authenticationForm.value != null) {
            AuthenticationScreen(
                authenticationForm = authenticationForm.value!!,
                downloadFile = {
                    context.downloadFile(url = it.url, fileName = it.name)
                },
                submitAuthenticationForm = {
                    viewModel.submitAuthenticationForm(
                        context = context,
                        userData = it.values.toList()
                    )
                },
                showExtensionError = {
                    Toast.makeText(
                        context,
                        "지원하지 않는 파일 형식입니다. hwp, hwpx, pdf 형식만 업로드 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                getFileName = {
                    val contentResolve = context.contentResolver
                    var fileName: String? = null

                    contentResolve.query(it, null, null, null, null)?.use { cursor ->
                        val nameIndex = cursor.getColumnIndex("_display_name")
                        cursor.moveToFirst()
                        fileName = cursor.getString(nameIndex)
                    }
                    val extension = MimeTypeMap.getSingleton()
                        .getExtensionFromMimeType(contentResolve.getType(it)) ?: ""
                    Pair(fileName ?: (UUID.randomUUID().toString() + "." + extension), extension)
                }
            )
        } else AuthenticationStatusComponent(verifyAuthenticationModel = verifyAuthenticationData.value)
    }
}