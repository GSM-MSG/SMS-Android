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
fun ProfileSection(setMajor: String, onClickMajorComponent: () -> Unit, onClickSearchBar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PicturePickerComponent(imageUrl = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4")
        SelfIntroduceComponent(introduceValue = "안드로이드의 왕이 될 남자")
        EmailComponent(emailValue = "s21042@gsm.hs.kr")
        MajorComponent(
            majorValue = "Android",
            isSelfTyping = setMajor == "직접입력",
            onClick = onClickMajorComponent,
            setMajorText = setMajor
        )
        PortfolioComponent(portfolioValue = "https://youtube.com")
        DetailTechStackComponent(addedList = listOf("AndroidStdio", "Kotlin", "Flutter"), onClickSearchBar = onClickSearchBar)
    }
}

@Preview
@Composable
private fun ProfileSectionPre() {
    ProfileSection(setMajor = "Android", onClickMajorComponent = {}, onClickSearchBar = {})
}