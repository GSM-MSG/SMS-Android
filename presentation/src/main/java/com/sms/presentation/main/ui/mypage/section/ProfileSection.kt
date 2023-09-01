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
import com.sms.presentation.main.ui.mypage.state.FormOfEmployment
import com.sms.presentation.main.ui.mypage.state.MilitaryService
import com.sms.presentation.main.ui.mypage.state.MyProfileData

@Composable
fun ProfileSection(
    myProfileData: MyProfileData,
    onEnteredMajorValue: (value: String) -> Unit,
    onValueChange: (value: MyProfileData) -> Unit,
    selectedTechList: List<String>,
    onClickMajorComponent: () -> Unit,
    onClickSearchBar: () -> Unit,
    onRemoveDetailStack: (value: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PicturePickerComponent(
            onChangeMyProfileImage = { onValueChange(myProfileData.copy(profileImageBitmap = it)) },
            imageUrl = myProfileData.profileImg,
            bitmapImage = myProfileData.profileImageBitmap,
        )
        SelfIntroduceComponent(introduceValue = myProfileData.introduce, onValueChange = {
            onValueChange(myProfileData.copy(introduce = it))
        })
        EmailComponent(emailValue = myProfileData.contactEmail, onValueChange = {
            onValueChange(myProfileData.copy(contactEmail = it))
        })
        MajorComponent(
            majorValue = if (myProfileData.major == "직접입력") myProfileData.enteredMajor else myProfileData.major,
            isSelfTyping = myProfileData.major == "직접입력",
            onClick = onClickMajorComponent,
            onValueChange = onEnteredMajorValue
        )
        PortfolioComponent(portfolioValue = myProfileData.portfolioUrl, onValueChange = {
            onValueChange(myProfileData.copy(portfolioUrl = it))
        })
        DetailTechStackComponent(
            addedList = selectedTechList,
            onClickSearchBar = onClickSearchBar,
            onRemoveDetailStack = onRemoveDetailStack
        )
    }
}

@Preview
@Composable
private fun ProfileSectionPre() {
    ProfileSection(
        myProfileData = MyProfileData(
            name = "",
            introduce = "",
            portfolioUrl = "",
            grade = 0,
            classNum = 0,
            number = 0,
            department = "",
            major = "",
            profileImg = "",
            contactEmail = "",
            gsmAuthenticationScore = 0,
            formOfEmployment = FormOfEmployment.NOT_SELECT,
            regions = listOf(),
            militaryService = MilitaryService.NOT_SELECT,
            salary = 0,
            languageCertificates = listOf(),
            certificates = listOf(),
            profileImageBitmap = null
        ),
        onValueChange = {},
        selectedTechList = listOf("Android Studio", "Kotlin", "Flutter"),
        onClickMajorComponent = {},
        onClickSearchBar = {},
        onRemoveDetailStack = {},
        onEnteredMajorValue = {}
    )
}