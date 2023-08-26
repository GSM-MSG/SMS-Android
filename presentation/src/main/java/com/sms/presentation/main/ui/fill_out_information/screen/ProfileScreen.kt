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
    profileImageUri: Uri,
    selectedMajor: String,
    detailStacks: List<String>,
    isImageExtensionInCorrect: Boolean,
    onPhotoPickBottomSheetOpenButtonClick: () -> Unit,
    onMajorBottomSheetOpenButtonClick: () -> Unit,
    onDialogDissmissButtonClick: () -> Unit,
    onSnackBarVisibleChanged: (text: String) -> Unit,
    onProfileTechStackValueChanged: (list: List<String>) -> Unit
) {
    val scrollState = rememberScrollState()
    val data = viewModel.getEnteredProfileInformation()
    val introduce = remember {
        mutableStateOf("")
    }
    val portfolioUrl = remember {
        mutableStateOf("")
    }
    val contactEmail = remember {
        mutableStateOf("")
    }
    val isRequired = remember {
        mutableStateOf(false)
    }
    val enteredMajor = remember {
        mutableStateOf(if (data.enteredMajor != "") data.enteredMajor else "")
    }
    val dialogState = remember {
        mutableStateOf(false)
    }

    if (isImageExtensionInCorrect) {
        SmsDialog(
            widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = onDialogDissmissButtonClick,
            importantButtonOnClick = onDialogDissmissButtonClick
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

    Column {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .background(Color.White)
        ) {
            SmsSpacer()
            ProfileComponent(
                isReadOnly = selectedMajor != "직접입력",
                selectedMajor = selectedMajor,
                savedData = { getIntroduce: String, getPortfolio: String, getContactEmail: String, getProfileImageUri: Uri ->
                    introduce.value = getIntroduce
                    portfolioUrl.value = getPortfolio
                    contactEmail.value = getContactEmail
                },
                enteredMajor = enteredMajor.value,
                data = data,
                isRequired = { result -> isRequired.value = result },
                profileImageUri = profileImageUri,
                changeView = {
                    if (detailStacks.size < 5) {
                        navController.navigate("Search")
                    } else {
                        onSnackBarVisibleChanged("세부스택은 최대 5개 까지 설정할 수 있습니다.")
                    }
                },
                enteringMajor = { string ->
                    enteredMajor.value = string
                },
                detailStacks = detailStacks,
                onPhotoPickBottomSheetOpenButtonClick = onPhotoPickBottomSheetOpenButtonClick,
                onMajorBottomSheetOpenButtonClick = onMajorBottomSheetOpenButtonClick,
                onProfileTechStackValueChanged = onProfileTechStackValueChanged
            )
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(32.dp))
                SmsRoundedButton(
                    text = "다음", modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isRequired.value && textFieldChecker(
                        if (selectedMajor == "직접입력") enteredMajor.value else selectedMajor
                    )
                ) {
                    if (contactEmail.value.isEmailRegularExpression() && portfolioUrl.value.isUrlRegularExpression()) {
                        viewModel.setEnteredProfileInformation(
                            major = selectedMajor,
                            techStack = detailStacks.joinToString(", "),
                            profileImgUri = profileImageUri,
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
