package com.sms.presentation.main.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.topbar.TopNavigation
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.authentication.AuthenticationFormModel
import com.msg.sms.domain.model.authentication.SendAuthenticationDataModel
import com.sms.presentation.main.ui.authentication.component.AuthenticationArea

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    authenticationForm: AuthenticationFormModel,
    onClickBackButton: () -> Unit,
) {
    val data = rememberSaveable {
        mutableStateOf((listOf<SendAuthenticationDataModel>()))
    }
    SMSTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colors.WHITE)
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
                AuthenticationArea(title = it.title, items = it.sections)
            }
        }
    }
}

@Preview
@Composable
private fun AuthenticationScreenPre() {
    AuthenticationScreen(authenticationForm = AuthenticationFormModel(listOf(), listOf())) {}
}