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
import com.sms.presentation.main.ui.fill_out_information.data.ProfileData
import com.sms.presentation.main.ui.util.isEmailRegularExpression
import com.sms.presentation.main.ui.util.isUrlRegularExpression
import com.sms.presentation.main.ui.util.textFieldChecker
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    data: ProfileData,
    profileImageUri: Uri,
    selectedMajor: String,
    detailStacks: List<String>,
    isImageExtensionInCorrect: Boolean,
    onPhotoPickBottomSheetOpenButtonClick: () -> Unit,
    onMajorBottomSheetOpenButtonClick: () -> Unit,
    onDialogDissmissButtonClick: () -> Unit,
    onSnackBarVisibleChanged: (text: String) -> Unit,
    onProjectValueChanged: (data: ProfileData) -> Unit
) {
    val scrollState = rememberScrollState()
    val isRequired = remember {
        mutableStateOf(false)
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

    LaunchedEffect("SnackBar") {
        if (detailStacks.size > 5) {
            onSnackBarVisibleChanged("스택 갯수를 초과하여 ${detailStacks.size - 5}개가 제외되었어요.")
        }
    }

    Column {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .background(Color.White)
        ) {
            SmsSpacer()
            ProfileComponent(
                data = data,
                isReadOnly = selectedMajor != "직접입력",
                selectedMajor = selectedMajor,
                enteredMajor = data.enteredMajor,
                profileImageUri = profileImageUri,
                detailStacks = if (detailStacks.size > 5) detailStacks.subList(0, 5) else detailStacks,
                changeView = {
                    if (detailStacks.size < 5) {
                        navController.navigate("Search")
                    } else {
                        onSnackBarVisibleChanged("세부스택은 최대 5개 까지 설정할 수 있습니다.")
                    }
                },
                onPhotoPickBottomSheetOpenButtonClick = onPhotoPickBottomSheetOpenButtonClick,
                onMajorBottomSheetOpenButtonClick = onMajorBottomSheetOpenButtonClick,
                isRequired = { result -> isRequired.value = result },
                onProfileValueChanged = onProjectValueChanged
            )
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(32.dp))
                SmsRoundedButton(
                    text = "다음", modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isRequired.value && textFieldChecker(
                        if (selectedMajor == "직접입력") data.enteredMajor else selectedMajor
                    )
                ) {
                    if (data.contactEmail.isEmailRegularExpression() && data.portfolioUrl.isUrlRegularExpression()) {
                        viewModel.setEnteredProfileInformation(
                            major = selectedMajor,
                            techStack = detailStacks,
                            profileImgUri = profileImageUri,
                            introduce = data.introduce,
                            contactEmail = data.contactEmail,
                            portfolioUrl = data.portfolioUrl,
                            enteredMajor = data.enteredMajor
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
