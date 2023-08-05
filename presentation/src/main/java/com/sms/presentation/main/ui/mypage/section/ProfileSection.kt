package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.profile.DetailTechStackComponent
import com.sms.presentation.main.ui.mypage.component.profile.EmailComponent
import com.sms.presentation.main.ui.mypage.component.profile.MajorComponent
import com.sms.presentation.main.ui.mypage.component.profile.PicturePickerComponent
import com.sms.presentation.main.ui.mypage.component.profile.PortfolioComponent
import com.sms.presentation.main.ui.mypage.component.profile.SelfIntroduceComponent

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PicturePickerComponent()
        SelfIntroduceComponent()
        EmailComponent()
        MajorComponent()
        PortfolioComponent()
        DetailTechStackComponent(addedList = listOf("AndroidStdio", "Kotlin", "Flutter"))
    }
}

@Preview
@Composable
private fun ProfileSectionPre() {
    ProfileSection()
}