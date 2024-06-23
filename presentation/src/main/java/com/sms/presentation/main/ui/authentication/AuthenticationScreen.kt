package com.sms.presentation.main.ui.authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.topbar.TopNavigation
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationFormModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel
import com.msg.sms.domain.model.authentication.response.FileModel
import com.sms.presentation.main.ui.authentication.component.AuthenticationArea
import com.sms.presentation.main.ui.authentication.component.FileDownLoadComponent

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    authenticationForm: AuthenticationFormModel,
    isClickAble: Boolean = true,
    downloadFile: (url: FileModel) -> Unit,
    onClickBackButton: () -> Unit,
    submitAuthenticationForm: (data: SubmitAuthenticationModel) -> Unit,
) {
    var authenticationFormList = rememberSaveable {
        mutableListOf<SubmitAuthenticationFormModel>()
    }
    SMSTheme { colors, _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            LazyColumn(
                modifier = modifier
                    .heightIn(max = 10000.dp)
                    .fillMaxWidth()
            ) {
                item {
                    TopNavigation(
                        text = "정보 입력",
                        leftIcon = { BackButtonIcon() },
                        onClickLeftButton = onClickBackButton
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colors.N10)
                            .height(16.dp)
                    )
                    FileDownLoadComponent(
                        file = authenticationForm.files.map { it.name },
                        onItemClick = { index ->
                            downloadFile(authenticationForm.files[index])
                        })
                }
                itemsIndexed(authenticationForm.contents) { index, it ->
                    if (index != authenticationForm.contents.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colors.N10)
                                .height(16.dp)
                        )
                    }
                    AuthenticationArea(
                        title = it.title,
                        items = it.sections,
                        onValueChanged = {
                            authenticationFormList = it.toMutableList()
                        })
                }
            }
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                visible = isClickAble
            ) {
                SmsRoundedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "저장",
                    onClick = {
                        submitAuthenticationForm(SubmitAuthenticationModel(contents = authenticationFormList))
                    },
                    enabled = true
                )
            }
        }
    }
}

@Preview
@Composable
private fun AuthenticationScreenPre() {
    AuthenticationScreen(
        authenticationForm = AuthenticationFormModel(listOf(), listOf()),
        downloadFile = {}, submitAuthenticationForm = {}, onClickBackButton = {})
}