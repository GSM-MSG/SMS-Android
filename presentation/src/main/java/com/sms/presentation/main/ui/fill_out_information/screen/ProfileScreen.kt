package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.profile.ProfileComponent
import com.sms.presentation.main.ui.util.isEmailRegularExpression
import com.sms.presentation.main.ui.util.isUrlRegularExpression
import com.sms.presentation.main.ui.util.textFieldChecker
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStack: List<String>,
    onPhotoPickBottomSheetOpenButtonClick: () -> Unit,
    onMajorBottomSheetOpenButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val data = viewModel.getEnteredProfileInformation()
    val selectedMajor = remember {
        mutableStateOf(if (data.major != "") data.major else "")
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
    val enteredMajor = remember {
        mutableStateOf(if (data.enteredMajor != "") data.enteredMajor else "")
    }
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }
    val dialogState = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val list = viewModel.getMajorListResponse.collectAsState()

    if (isImageExtensionInCorrect.value) {
        SmsDialog(
            widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            importantButtonOnClick = { isImageExtensionInCorrect.value = false }
        )
    }

    if (dialogState.value) {
        SmsDialog(
            widthPercent = 1f,
            betweenTextAndButtonHeight = 37.dp,
            title = "에러",
            msg = "이메일 형식또는 url형식을 확인해 주세요.",
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = { dialogState.value = false },
            importantButtonOnClick = { dialogState.value = false }
        )
    }

//    bottomSheetContent(
//        content = {
//            if (isProfilePictureBottomSheet.value) {
//
//            } else {
//                SelectorBottomSheet(
//                    list = if (list.value.data != null) list.value.data!!.major else listOf(
//                        ""
//                    ),
//                    bottomSheetState = bottomSheetState,
//                    selected = selectedMajor.value,
//                    itemChange = {
//                        selectedMajor.value = it
//                        Log.d("major", it)
//                    },
//                    lastItem = {
//                        MajorSelector(
//                            major = "직접입력",
//                            selected = selectedMajor.value == "직접입력"
//                        ) {
//                            selectedMajor.value = "직접입력"
//                            scope.launch {
//                                bottomSheetState.hide()
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    )

    Column {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .background(Color.White)
        ) {
            SmsSpacer()
            ProfileComponent(
                isReadOnly = selectedMajor.value != "직접입력",
                selectedMajor = selectedMajor.value,
                savedData = { getIntroduce: String, getPortfolio: String, getContactEmail: String, getProfileImageUri: Uri ->
                    introduce.value = getIntroduce
                    portfolioUrl.value = getPortfolio
                    contactEmail.value = getContactEmail
                    profileImageUri.value = getProfileImageUri
                },
                enteredMajor = enteredMajor.value,
                data = data,
                isRequired = { result -> isRequired.value = result },
                //isEnable = list.value.data != null,
                profileImageUri = profileImageUri.value,
                changeView = {
                    viewModel.setEnteredProfileInformation(
                        major = selectedMajor.value,
                        techStack = detailStack.joinToString(", "),
                        profileImgUri = profileImageUri.value,
                        introduce = introduce.value,
                        contactEmail = contactEmail.value,
                        portfolioUrl = portfolioUrl.value,
                        enteredMajor = enteredMajor.value
                    )
                    navController.navigate("Search/Profile")
                },
                enteringMajor = { string ->
                    enteredMajor.value = string
                },
                detailStack = detailStack.joinToString(", "),
                onPhotoPickBottomSheetOpenButtonClick = onPhotoPickBottomSheetOpenButtonClick,
                onMajorBottomSheetOpenButtonClick = onMajorBottomSheetOpenButtonClick
            )
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(32.dp))
                SmsRoundedButton(
                    text = "다음", modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isRequired.value && textFieldChecker(
                        if (selectedMajor.value == "직접입력") enteredMajor.value else selectedMajor.value
                    )
                ) {
                    if (contactEmail.value.isEmailRegularExpression() && portfolioUrl.value.isUrlRegularExpression()) {
                        viewModel.setEnteredProfileInformation(
                            major = selectedMajor.value,
                            techStack = detailStack.joinToString(", "),
                            profileImgUri = profileImageUri.value,
                            introduce = introduce.value,
                            contactEmail = contactEmail.value,
                            portfolioUrl = portfolioUrl.value,
                            enteredMajor = enteredMajor.value
                        )
                        navController.navigate("SchoolLife")
                    } else {
                        dialogState.value = true
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
