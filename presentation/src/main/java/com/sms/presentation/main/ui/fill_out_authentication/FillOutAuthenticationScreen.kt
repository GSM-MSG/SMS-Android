package com.sms.presentation.main.ui.fill_out_authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.icon.BlackLogoutIcon
import com.sms.presentation.main.ui.fill_out_authentication.section.AuthenticationSection
import com.sms.presentation.main.ui.fill_out_authentication.state.AuthenticationData
import com.sms.presentation.main.ui.mypage.component.button.SaveButtonComponent

@Composable
fun FillOutAuthenticationScreen(
    authenticationData: AuthenticationData,
    onValueChange: (value: AuthenticationData) -> Unit,
    onClickTopLeftButton: () -> Unit,
    onClickTopRightButton: () -> Unit,
    onSaveButtonClick: () -> Unit
) {
    val isButtonClicked = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBarComponent(
            text = "마이페이지",
            leftIcon = { BackButtonIcon() },
            rightIcon = { BlackLogoutIcon() },
            onClickRightButton = onClickTopRightButton,
            onClickLeftButton = onClickTopLeftButton,
        )
        SmsSpacer()
        TitleHeader(titleText = "인증제 *")
        AuthenticationSection(
            modifier = Modifier.weight(1f),
            authenticationData = authenticationData,
            onValueChange = onValueChange
        )
        SaveButtonComponent(
            modifier = Modifier.padding(horizontal = 20.dp),
            visibility = true,
            enabled = !isButtonClicked.value,
            onClickSaveButton = {
                isButtonClicked.value = true
                onSaveButtonClick()
            }
        )
    }
}

@Preview
@Composable
private fun FillOutAuthenticationScreenPre() {
    FillOutAuthenticationScreen(
        authenticationData = AuthenticationData(
            title = "",
            content = "",
            activityImages = "",
            activityImagesBitmap = null
        ),
        onValueChange = {},
        onClickTopLeftButton = {},
        onClickTopRightButton = {},
        onSaveButtonClick = {}
    )
}