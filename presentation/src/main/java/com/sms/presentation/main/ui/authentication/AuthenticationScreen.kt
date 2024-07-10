package com.sms.presentation.main.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
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
    downloadFile: (url: FileModel) -> Unit,
    onClickBackButton: () -> Unit,
    submitAuthenticationForm: (data: SubmitAuthenticationModel) -> Unit,
) {
    var authenticationFormList = rememberSaveable {
        mutableListOf<SubmitAuthenticationFormModel>()
    }
    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            LazyColumn(
                modifier = modifier
                    .heightIn(max = 10000.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                item {
                    TopNavigation(
                        text = "인증제",
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
                        modifier = Modifier.padding(24.dp),
                        file = authenticationForm.files.map { it.name },
                        onItemClick = { index ->
                            downloadFile(authenticationForm.files[index])
                        })
                }
                itemsIndexed(authenticationForm.contents) { index, it ->
                    AuthenticationArea(
                        title = it.title,
                        items = it.sections,
                        onValueChanged = {
                            authenticationFormList = it.toMutableList()
                        })
                    if (index != authenticationForm.contents.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colors.N10)
                                .height(16.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .background(color = colors.N10)
                            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 72.dp),
                    ) {
                        SmsRoundedButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "저장",
                            onClick = {
                                submitAuthenticationForm(SubmitAuthenticationModel(contents = authenticationFormList))
                            },
                        )
                    }
                }
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