package com.sms.presentation.main.ui.fill_out_information.screen

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.bottomsheet.ChooseProfilePictureBottomSheet
import com.msg.sms.design.component.bottomsheet.SelectorBottomSheet
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.selector.MajorSelector
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.sms.presentation.main.ui.fill_out_information.component.ProfileComponent
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import com.sms.presentation.main.ui.util.toUri
import com.sms.presentation.main.viewmodel.FillOutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStack: List<String>,
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

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
    val isProfilePictureBottomSheet = remember {
        mutableStateOf(true)
    }
    val isCamera = remember {
        mutableStateOf(false)
    }
    val enteredMajor = remember {
        mutableStateOf(if (data.enteredMajor != "") data.enteredMajor else "")
    }
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                    isImageExtensionInCorrect.value = false
                    profileImageUri.value = uri
                } else {
                    isImageExtensionInCorrect.value = true
                }
            }
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            val uri = bitmap.toUri(context)
            if (uri != null) {
                if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                    isImageExtensionInCorrect.value = false
                    profileImageUri.value = uri
                } else {
                    isImageExtensionInCorrect.value = true
                }
            }
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted && isCamera.value -> {
                cameraLauncher.launch(null)
            }
            isGranted && !isCamera.value -> {
                galleryLauncher.launch("image/*")
            }
        }
    }
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    val list = viewModel.getMajorListEvent.collectAsState()

    if (isImageExtensionInCorrect.value) {
        SmsDialog(
            widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            normalButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            normalButtonOnClick = { isImageExtensionInCorrect.value = false }
        )
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (isProfilePictureBottomSheet.value) {
                ChooseProfilePictureBottomSheet(
                    bottomSheetState = bottomSheetState,
                    isCamera = {
                        isCamera.value = it
                    },
                    permissionLauncher = permissionLauncher,
                    permission = permission
                )
            } else {
                SelectorBottomSheet(
                    list = if (list.value.data != null) list.value.data!!.major else listOf(""),
                    bottomSheetState = bottomSheetState,
                    selected = selectedMajor.value,
                    itemChange = {
                        selectedMajor.value = it
                        Log.d("major", it)
                    },
                    lastItem = {
                        MajorSelector(major = "직접입력", selected = selectedMajor.value == "직접입력") {
                            selectedMajor.value = "직접입력"
                            scope.launch {
                                bottomSheetState.hide()
                            }
                        }
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
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .background(Color.White)) {
                ProfileComponent(
                    bottomSheetScaffoldState = bottomSheetState,
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
                    isEnable = list.value.data != null,
                    profileImageUri = profileImageUri.value,
                    isProfilePictureBottomSheet = { isProfilePictureBottomSheet.value = it },
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
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "detailStack",
                            value = detailStack.joinToString(",")
                        )
                        navController.navigate("Search")
                    },
                    enteringMajor = { string ->
                        enteredMajor.value = string
                    },
                    detailStack = detailStack.joinToString(", ")
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
                            "whenClickNextButton: ${selectedMajor.value}, ${detailStack}, ${introduce.value}, ${portfolioUrl.value}, ${contactEmail.value}, ${profileImageUri.value}"
                        )
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
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}