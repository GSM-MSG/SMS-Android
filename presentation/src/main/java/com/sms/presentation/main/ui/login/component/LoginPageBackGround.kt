package com.sms.presentation.main.ui.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sms.presentation.R

@Composable
fun LoginPageBackGround() {
    Image(
        painter = painterResource(id = R.drawable.login_page_bg),
        contentDescription = "login page background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}