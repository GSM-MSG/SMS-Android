package com.sms.presentation.main.ui.fill_out_information

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.lottie.SmsLoadingLottie
import com.msg.sms.design.component.snackbar.SmsSnackBar
import com.msg.sms.design.icon.ExclamationMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.request.*
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.fill_out_information.component.FillOutInformationTopBarComponent
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.MajorSelectorBottomSheet
import com.sms.presentation.main.ui.fill_out_information.component.bottomsheet.PhotoPickBottomSheet
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.MainActivity
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private enum class BottomSheetValues {
    PhotoPicker,
    Major,
}

private enum class DetailSearchLocation {
    Profile,
}

enum class FillOutPage(val value: String) {
    Profile("Profile"),
    Search("Search"),
}

@AndroidEntryPoint
class FillOutInformationActivity : BaseActivity() {
    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val searchDetailStack = mutableStateOf(listOf<String>())

    @OptIn(ExperimentalMaterialApi::class)
    override fun init() {
        fillOutViewModel.getMajorList()
        lifecycleScope.launch {
            searchDetailStack()
        }

        setContent {
            val focusManager = LocalFocusManager.current
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val bottomSheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val bottomSheetValues = remember {
                mutableStateOf(BottomSheetValues.Major)
            }
            val detailStackSearchLocation = remember {
                mutableStateOf(DetailSearchLocation.Profile)
            }
            val currentRoute = remember {
                mutableStateOf(FillOutPage.Profile.value)
            }
            val snackBarVisible = remember {
                mutableStateOf(false)
            }
            val snackBarText = remember {
                mutableStateOf("")
            }

            //enteredData
            val enteredProfileData = fillOutViewModel.getEnteredProfileInformation()

            //data
            val profileData = remember {
                mutableStateOf(enteredProfileData)
            }
            val majorList = fillOutViewModel.getMajorListResponse.collectAsState()

            //DetailStacks
            val profileDetailTechStack = remember {
                mutableStateListOf(
                    *(enteredProfileData.techStack.filter { it != "" }).toTypedArray()
                )
            }

            //PhotoPickBottomSheet
            val profileImageUri = remember {
                mutableStateOf(Uri.EMPTY)
            }
            val isImageExtensionInCorrect = remember {
                mutableStateOf(false)
            }

            //MajorSelectorBottomSheet
            val selectedMajor = remember {
                mutableStateOf("")
            }

            //Dialog
            val dialogVisible = remember {
                mutableStateOf(false)
            }
            val dialogTitle = remember {
                mutableStateOf("")
            }
            val dialogText = remember {
                mutableStateOf("")
            }
            val loadingModalState = remember {
                mutableStateOf(false)
            }
            val isUnauthorized = remember {
                mutableStateOf(false)
            }

            if (loadingModalState.value) {
                Dialog(onDismissRequest = { }) {
                    SmsLoadingLottie(modifier = Modifier.size(80.dp))
                }
            }

            if (dialogVisible.value) {
                SmsDialog(
                    title = dialogTitle.value,
                    msg = dialogText.value,
                    outLineButtonText = "취소",
                    importantButtonText = "확인",
                    outlineButtonOnClick = { dialogVisible.value = false },
                    importantButtonOnClick = {
                        dialogVisible.value = false
                        if (isUnauthorized.value) {
                            authViewModel.deleteToken()
                            startActivity(
                                Intent(
                                    this@FillOutInformationActivity,
                                    LoginActivity::class.java
                                )
                            )
                            finish()
                        }
                    }
                )
            }

            ModalBottomSheetLayout(
                sheetContent = {
                    when (bottomSheetValues.value) {
                        BottomSheetValues.PhotoPicker -> {
                            PhotoPickBottomSheet(
                                bottomSheetState = bottomSheetState,
                                onProfileImageUriChanged = { uri, isImageExtensionCorrect ->
                                    isImageExtensionInCorrect.value = !isImageExtensionCorrect
                                    if (isImageExtensionCorrect) {
                                        profileImageUri.value = uri
                                    }
                                }
                            )
                        }

                        BottomSheetValues.Major -> {
                            MajorSelectorBottomSheet(
                                bottomSheetState = bottomSheetState,
                                majorList = if (majorList.value.data != null) majorList.value.data!!.major else listOf(),
                                selectedMajor = selectedMajor.value,
                                onSelectedMajhorChange = {
                                    selectedMajor.value = it
                                },
                            )
                        }
                    }
                },
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetState = bottomSheetState
            ) {
                SMSTheme { colors, _ ->
                    Box {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colors.WHITE)
                        ) {
                            FillOutInformationTopBarComponent(currentRoute = currentRoute.value) {
                                navController.popBackStack()
                            }
                            NavHost(
                                navController = navController,
                                startDestination = FillOutPage.Profile.value
                            ) {
                                composable(FillOutPage.Profile.value) {
                                    currentRoute.value = FillOutPage.Profile.value
                                    setSoftInputMode("PAN")
                                    ProfileScreen(
                                        navController = navController,
                                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                                        data = profileData.value,
                                        detailStacks = profileDetailTechStack,
                                        profileImageUri = profileImageUri.value,
                                        selectedMajor = selectedMajor.value,
                                        isImageExtensionInCorrect = isImageExtensionInCorrect.value,
                                        onPhotoPickBottomSheetOpenButtonClick = {
                                            bottomSheetValues.value = BottomSheetValues.PhotoPicker
                                            scope.launch { bottomSheetState.show() }
                                        },
                                        onMajorBottomSheetOpenButtonClick = {
                                            if (majorList.value.data != null) {
                                                bottomSheetValues.value = BottomSheetValues.Major
                                                scope.launch { bottomSheetState.show() }
                                            }
                                        },
                                        onDialogDissmissButtonClick = {
                                            isImageExtensionInCorrect.value = false
                                        },
                                        onSnackBarVisibleChanged = { text ->
                                            scope.launch {
                                                snackBarVisible.value = true
                                                snackBarText.value = text
                                                delay(1.5.seconds)
                                                focusManager.clearFocus()
                                                snackBarVisible.value = false
                                            }
                                        },
                                        onProfileValueChanged = {
                                            profileData.value = it
                                        },
                                        onTechStackItemRemoved = {
                                            profileDetailTechStack.remove(it)
                                        },
                                        onCompleteButtonClick = { data ->
                                            loadingModalState.value = true

                                            //이미지 업로드 & 정보기입 요청
                                            lifecycleScope.launch {
                                                val profileImageUpload =
                                                    fillOutViewModel.profileImageUploadAsync(
                                                        profileImageUri.value,
                                                        this@FillOutInformationActivity
                                                    )

                                                awaitAll(
                                                    profileImageUpload,
                                                )

                                                if (fillOutViewModel.profileImageUploadResponse.value is Event.Success) {
                                                    fillOutViewModel.enterStudentInformation(
                                                        major = data.major.takeIf { it != "직접입력" }
                                                            ?: data.enteredMajor,
                                                        techStack = data.techStack,
                                                        profileImgUrl = fillOutViewModel.profileImageUploadResponse.value.data!!,
                                                        introduce = data.introduce,
                                                        contactEmail = data.contactEmail
                                                    )
                                                }
                                            }

                                            //예외처리
                                            lifecycleScope.launch {
                                                enteredStudentInfomationResponse(
                                                    viewModel = fillOutViewModel,
                                                    onSuccess = {
                                                        loadingModalState.value = false
                                                        startActivity(
                                                            Intent(
                                                                this@FillOutInformationActivity,
                                                                MainActivity::class.java
                                                            )
                                                        )
                                                        finish()
                                                    },
                                                    error = { errorMsg, unauthorized ->
                                                        loadingModalState.value = false
                                                        dialogVisible.value = true
                                                        isUnauthorized.value = unauthorized
                                                        dialogTitle.value = "실패"
                                                        dialogText.value = errorMsg
                                                    }
                                                )
                                                profileImageUploadResponse(fillOutViewModel) { errorMsg ->
                                                    dialogVisible.value = true
                                                    dialogText.value = "실패"
                                                    dialogText.value = errorMsg
                                                }
                                            }
                                        }
                                    )
                                }
                                composable(FillOutPage.Search.value) {
                                    currentRoute.value = FillOutPage.Search.value
                                    setSoftInputMode("RESIZE")
                                    DetailStackSearchScreen(
                                        navController = navController,
                                        detailStack = searchDetailStack.value,
                                        selectedStack = when (detailStackSearchLocation.value) {
                                            DetailSearchLocation.Profile -> profileDetailTechStack
                                        },
                                        onSearchStack = {
                                            searchDetailStackViewModel.searchDetailStack(it)
                                        }
                                    ) { stack ->
                                        when (detailStackSearchLocation.value) {
                                            DetailSearchLocation.Profile -> {
                                                profileDetailTechStack.removeAll(
                                                    profileDetailTechStack.filter {
                                                        !stack.contains(it)
                                                    })
                                                profileDetailTechStack.addAll(stack.filter {
                                                    !profileDetailTechStack.contains(it)
                                                })
                                            }
                                        }
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                        SmsSnackBar(
                            text = snackBarText.value,
                            visible = snackBarVisible.value,
                            leftIcon = { ExclamationMarkIcon() },
                            onClick = { snackBarVisible.value = false },
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }

    private suspend fun enteredStudentInfomationResponse(
        viewModel: FillOutViewModel,
        onSuccess: () -> Unit,
        error: (errorMsg: String, isUnauthorized: Boolean) -> Unit,
    ) {
        viewModel.enterInformationResponse.collect { response ->
            when (response) {
                is Event.Success -> {
                    onSuccess()
                }

                is Event.Unauthorized -> {
                    error("토큰이 만료되었습니다, 다시 로그인 하시겠습니까?", true)
                }

                is Event.Conflict -> {
                    error("이미 존재하는 유저입니다.", false)
                }

                is Event.Server -> {
                    error("서버 에러 발생, 개발자에게 문의해주세요.", false)
                }

                is Event.Loading -> {}
                else -> {
                    error("알 수 없는 에러 발생, 개발자에게 문의해주세요.", false)
                }
            }
        }
    }

    private suspend fun profileImageUploadResponse(
        viewModel: FillOutViewModel,
        error: (errorMsg: String) -> Unit
    ) {
        viewModel.profileImageUploadResponse.collect { response ->
            when (response) {
                is Event.Success, Event.Loading -> {}
                is Event.BadRequest -> {
                    error("이미지 업로드 실패, 개발자에게 문의해주세요.")
                }

                is Event.Server -> {
                    error("서버 에러 발생, 개발자에게 문의해 주세요.")
                }

                else -> {
                    error("알 수 없는 에러 발생, 개발자에게 문의해 주세요.")
                }
            }
        }
    }

    private suspend fun searchDetailStack() {
        searchDetailStackViewModel.searchResultEvent.collect {
            when (it) {
                is Event.Success -> {
                    searchDetailStack.value = it.data!!.techStacks
                }

                else -> {}
            }
        }
    }
}