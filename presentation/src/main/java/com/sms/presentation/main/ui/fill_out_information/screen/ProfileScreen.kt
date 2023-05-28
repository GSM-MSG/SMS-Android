package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.bottomsheet.ChooseProfilePictureBottomSheet
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.ProfileComponent
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
) {
    val scrollState = rememberScrollState()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val selectedMajor = remember {
        mutableStateOf("")
    }
    val techStack = remember {
        mutableStateOf("")
    }
    val introduce = remember {
        mutableStateOf("")
    }
    val portfolioUrl = remember {
        mutableStateOf("")
    }
    val contactEmail = remember {
        mutableStateOf("")
    }
    val profileImageUri = remember {
        mutableStateOf(Uri.EMPTY)
    }
    val isRequired = remember {
        mutableStateOf(false)
    }
    val isProfilePictureBottomSheet = remember {
        mutableStateOf(true)
    }
    val list = viewModel.getMajorListEvent.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
            if (isProfilePictureBottomSheet.value) {
                ChooseProfilePictureBottomSheet(
                    bottomSheetState = bottomSheetState,
                    profileImageUri = {
                        profileImageUri.value = it
                    })
            } else {
                SelectorBottomSheet(
                    list = if (list.value.data != null) list.value.data!!.major else listOf(""),
                    bottomSheetState = bottomSheetState,
                    selected = selectedMajor.value,
                    itemChange = {
                        selectedMajor.value = it
                    }
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState,
    ) {
        Column {
            TopBarComponent(
                text = "정보입력",
                leftIcon = { BackButtonIcon() },
                rightIcon = null,
                onClickLeftButton = {
                }) {
            }
            SmsSpacer()
            Column(Modifier.verticalScroll(scrollState)) {
                ProfileComponent(
                    bottomSheetState,
                    selectedMajor.value,
                    enteredData = { getTechStack: String, getIntroduce: String, getPortfolio: String, getContactEmail: String, getProfileImageUri: Uri ->
                        techStack.value = getTechStack
                        introduce.value = getIntroduce
                        portfolioUrl.value = getPortfolio
                        contactEmail.value = getContactEmail
                        profileImageUri.value = getProfileImageUri
                    },
                    viewModel.getEnteredProfileInformation(),
                    { result -> isRequired.value = result },
                    isEnable = list.value.data != null,
                    profileImageUri = profileImageUri.value,
                    isProfilePictureBottomSheet = { isProfilePictureBottomSheet.value = it }
                )
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Spacer(modifier = Modifier.height(32.dp))
                    SmsRoundedButton(
                        text = "다음", modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = isRequired.value
                    ) {
                        Log.d(
                            "TAG",
                            "ProfileScreen: ${selectedMajor.value}, ${techStack.value}, ${introduce.value}, ${portfolioUrl.value}, ${contactEmail.value}, ${profileImageUri.value}"
                        )
                        viewModel.setEnteredProfileInformation(
                            major = selectedMajor.value,
                            techStack = techStack.value,
                            profileImgUri = profileImageUri.value,
                            introduce = introduce.value,
                            contactEmail = contactEmail.value,
                            portfolioUrl = portfolioUrl.value
                        )
                        navController.navigate("SchoolLife")
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}