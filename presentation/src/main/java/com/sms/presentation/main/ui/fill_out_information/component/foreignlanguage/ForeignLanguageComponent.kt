package com.sms.presentation.main.ui.fill_out_information.component.foreignlanguage

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.lottie.SmsLoadingLottie
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.util.isEmailRegularExpression
import com.sms.presentation.main.ui.util.isUrlRegularExpression
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope

@Composable
fun ForeignLanguageComponent(
    navController: NavController,
    viewModel: FillOutViewModel,
    lifecycleScope: CoroutineScope,
) {
    SMSTheme { colors, typography ->
        val enteredProfileData = viewModel.getEnteredProfileInformation()
        val enteredSchoolLifeData = viewModel.getEnteredSchoolLifeInformation()
        val enteredMilitaryServiceData = viewModel.getEnteredMilitaryServiceInformation()
        val enteredWorkConditionData = viewModel.getEnteredWorkConditionInformation()
        val enteredCertificateData = viewModel.getEnteredCertification()
        val context = LocalContext.current as FillOutInformationActivity
        val foreignLanguageList = remember {
            mutableStateListOf("")
        }
        val foreignLanguageScoreList = remember {
            mutableStateListOf("")
        }
        val dialogState = remember {
            mutableStateOf(false)
        }
        val errorTitle = remember {
            mutableStateOf("")
        }
        val errorMsg = remember {
            mutableStateOf("")
        }
        val onClick = remember {
            mutableStateOf({})
        }
        val loadingModalState = remember {
            mutableStateOf(false)
        }
        if (loadingModalState.value) {
            Dialog(onDismissRequest = { }) {
                SmsLoadingLottie(modifier = Modifier.size(80.dp))
            }
        }

        if (dialogState.value) {
            SmsDialog(
                widthPercent = 1f,
                betweenTextAndButtonHeight = 37.dp,
                cancelButtonEnabled = false,
                title = errorTitle.value,
                msg = errorMsg.value,
                outLineButtonText = "취소",
                normalButtonText = "확인",
                outlineButtonOnClick = {
                    dialogState.value = false
                },
                normalButtonOnClick = {
                    onClick.value()
                    dialogState.value = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            SmsTitleText(text = "외국어", isRequired = false)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "외국어", style = typography.body2, color = colors.N40)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(foreignLanguageList.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SmsTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            setText = foreignLanguageList[it],
                            onClickButton = {
                                foreignLanguageList[it] = ""
                            },
                            maxLines = 1,
                            placeHolder = "예) 토익",
                            onValueChange = { str ->
                                foreignLanguageList[it] = str.trim()
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SmsTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.7f),
                            setText = foreignLanguageScoreList[it],
                            onClickButton = {
                                foreignLanguageScoreList[it] = ""
                            },
                            maxLines = 1,
                            placeHolder = "990",
                            onValueChange = { str ->
                                foreignLanguageScoreList[it] = str.trim()
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = {
                            foreignLanguageList.removeAt(it)
                            foreignLanguageScoreList.removeAt(it)
                        }) {
                            TrashCanIcon(modifier = Modifier.size(24.dp))
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .padding(start = 8.dp, top = 5.5.dp, bottom = 5.5.dp, end = 5.5.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                foreignLanguageScoreList.add("")
                                foreignLanguageList.add("")
                            }) {
                        Text(text = "+", style = typography.body1, color = colors.N30)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "추가", style = typography.body1, color = colors.N30)
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(2f),
                        text = "이전",
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(4f),
                        text = "다음",
                        state = ButtonState.Normal
                    ) {
//                        loadingModalState.value = true
//                        val foreignLanguage =
//                            foreignLanguageList.mapIndexed { index: Int, name: String ->
//                                CertificateInformationModel(
//                                    languageCertificateName = name,
//                                    score = foreignLanguageScoreList[index]
//                                )
//                            }
//                        lifecycleScope.launch {
//                            viewModel.imageUpload(
//                                enteredProfileData.profileImageUri.toMultipartBody(
//                                    context
//                                )!!
//                            )
//                            imageFileUpload(
//                                viewModel = viewModel,
//                                dialog = { errorState, title, msg ->
//                                    dialogState.value = errorState
//                                    errorTitle.value = title
//                                    errorMsg.value = msg
//                                },
//                                isUnauthorized = {
//                                    onClick.value = {
//                                        context.startActivity(
//                                            Intent(
//                                                context,
//                                                LoginActivity::class.java
//                                            )
//                                        )
//                                        context.finish()
//                                    }
//                                },
//                                isBadRequest = {
//                                    onClick.value = {
//                                        navController.navigate("Profile")
//                                    }
//                                }
//                            )
//                        }
//
//                        lifecycleScope.launch {
//                            viewModel.fileUploadCompleted.collect { isComplete ->
//                                Log.d("fileUploadCompleted", isComplete.toString())
//                                if (isComplete) {
//                                    viewModel.enterStudentInformation(
//                                        major = if (enteredProfileData.major == "직접입력") enteredProfileData.enteredMajor else enteredProfileData.major,
//                                        techStack = enteredProfileData.techStack.split(",")
//                                            .map { it.trim() },
//                                        profileImgUrl = viewModel.getProfileImageUrl(),
//                                        introduce = enteredProfileData.introduce,
//                                        portfolioUrl = enteredProfileData.portfolioUrl,
//                                        contactEmail = enteredProfileData.contactEmail,
//                                        formOfEmployment = enteredWorkConditionData.formOfEmployment.toEnum(),
//                                        gsmAuthenticationScore = enteredSchoolLifeData.gsmAuthenticationScore.toInt(),
//                                        salary = enteredWorkConditionData.salary.toInt(),
//                                        region = enteredWorkConditionData.region,
//                                        languageCertificate = foreignLanguage,
//                                        militaryService = enteredMilitaryServiceData.militaryService.toEnum(),
//                                        certificate = enteredCertificateData.certification
//                                    )
//                                }
//                            }
//                        }
//
//                        lifecycleScope.launch {
//                            enterStudentInformation(
//                                viewModel = viewModel,
//                                dialog = { visible, title, msg ->
//                                    dialogState.value = visible
//                                    errorTitle.value = title
//                                    errorMsg.value = msg
//                                },
//                                isSuccess = {
//                                    onClick.value = {
//                                        context.startActivity(
//                                            Intent(
//                                                context,
//                                                MainActivity::class.java
//                                            )
//                                        )
//                                        context.finish()
//                                    }
//                                },
//                                onDialogButtonClick = {
//                                    onClick.value = {
//                                        navController.navigate("Profile")
//                                    }
//                                }
//                            )
//                        }
                        navController.navigate("Projects")
                        //TODO (kim hyunseung) - 서버 통신 관련 로직들 수상경력 페이지 퍼블리싱 후에 그쪽으로 이전하기
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

suspend fun imageFileUpload(
    viewModel: FillOutViewModel,
    dialog: (visible: Boolean, title: String, msg: String) -> Unit,
    isUnauthorized: () -> Unit,
    isBadRequest: () -> Unit,
) {
    viewModel.imageUploadResponse.collect { response ->
        viewModel.specifyWhenCompleteFileUpload()
        when (response) {
            is Event.Success -> {
                viewModel.setProfileImageUrl(response.data!!.fileUrl)
            }
            is Event.Unauthorized -> {
                dialog(true, "토큰 만료", "다시 로그인 해주세요")
                isUnauthorized()
            }
            is Event.BadRequest -> {
                dialog(true, "에러", "파일이 jpg, jpeg, png, heic 가 아닙니다.")
                isBadRequest()
            }
            is Event.Loading -> {}
            else -> {
                dialog(true, "에러", "알 수 없는 오류 발생")
            }
        }
    }
}

suspend fun enterStudentInformation(
    viewModel: FillOutViewModel,
    dialog: (visible: Boolean, title: String, msg: String) -> Unit,
    onDialogButtonClick: () -> Unit,
    isSuccess: () -> Unit,
) {
    viewModel.enterInformationResponse.collect { response ->
        Log.d("정보기입", response.toString())
        when (response) {
            is Event.Success -> {
                dialog(true, "성공", "정보기입을 완료했습니다.")
                isSuccess()
            }

            is Event.BadRequest -> {
                if (!viewModel.getEnteredProfileInformation().contactEmail.isEmailRegularExpression()) {
                    dialog(true, "에러", "이메일 형식이 맞지 않습니다. \n수정하시겠습니까?")
                    onDialogButtonClick()
                } else if (!viewModel.getEnteredProfileInformation().portfolioUrl.isUrlRegularExpression()) {
                    dialog(true, "에러", "포트폴리오 Url이 형식에 맞지 않습니다. \n수정하시겠습니까?")
                    onDialogButtonClick()
                } else {
                    dialog(true, "에러", "이메일 형식또는 url형식이 맞지 않습니다. \n수정하시겠습니까?")
                    onDialogButtonClick()
                }
            }

            is Event.Conflict -> {
                dialog(true, "에러", "이미 존재하는 유저 입니다.")
            }

            is Event.Loading -> {}
            else -> {
                dialog(true, "에러", "알 수 없는 오류 발생")
            }
        }
    }
}

private fun String.toEnum(): String {
    return when (this) {
        "정규직" -> "FULL_TIME"
        "비정규직" -> "TEMPORARY"
        "계약직" -> "CONSTRACT"
        "인턴" -> "INTERN"
        "병특 희망" -> "HOPE"
        "희망하지 않음" -> "NOT_HOPE"
        "상관없음" -> "NO_MATTER"
        "해당 사항 없음" -> "NONE"
        else -> ""
    }
}