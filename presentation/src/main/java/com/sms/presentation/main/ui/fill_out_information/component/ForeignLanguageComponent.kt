package com.sms.presentation.main.ui.fill_out_information.component

import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ForeignLanguageComponent(
    navController: NavController,
    viewModel: FillOutViewModel,
    lifecycleScope: CoroutineScope
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
        val isError = remember {
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

        if (isError.value) {
            SmsDialog(
                widthPercent = 1f,
                title = errorTitle.value,
                msg = errorMsg.value,
                outLineButtonText = "취소",
                normalButtonText = "확인",
                outlineButtonOnClick = {
                    isError.value = false
                },
                normalButtonOnClick = {
                    onClick.value()
                    isError.value = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SmsTitleText(text = "외국어", isRequired = false)
                PagerIndicator(indexOfPointingNumber = 5, size = 6)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "외국어", style = typography.body2, color = colors.N40)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(foreignLanguageList.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SmsCustomTextField(
                            modifier = Modifier
                                .weight(41f),
                            clickAction = {},
                            placeHolder = "예) 토익",
                            endIcon = null,
                            onValueChange = { str ->
                                foreignLanguageList[it] = str
                            },
                            setChangeText = foreignLanguageList[it]
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SmsCustomTextField(
                            modifier = Modifier
                                .weight(23f),
                            clickAction = {},
                            placeHolder = "990",
                            endIcon = null,
                            onValueChange = { str -> foreignLanguageScoreList[it] = str },
                            setChangeText = foreignLanguageScoreList[it]
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
                            .weight(2f)
                            .height(48.dp),
                        text = "이전",
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(4f)
                            .height(48.dp),
                        text = "다음",
                        state = ButtonState.Normal
                    ) {
                        val foreignLanguage =
                            foreignLanguageList.mapIndexed { index: Int, name: String ->
                                CertificateInformationModel(
                                    languageCertificateName = name,
                                    score = foreignLanguageScoreList[index]
                                )
                            }

                        lifecycleScope.launch {

                            viewModel.imageUpload(
                                enteredProfileData.profileImageUri.toMultipartBody(
                                    context
                                )!!
                            )
                            imageFileUpload(
                                viewModel = viewModel,
                                error = { errorState, title, msg ->
                                    isError.value = errorState
                                    errorTitle.value = title
                                    errorMsg.value = msg
                                },
                                isUnauthorized = {
                                    onClick.value = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                LoginActivity::class.java
                                            )
                                        )
                                        context.finish()
                                    }
                                },
                                isBadRequest = {
                                    onClick.value = {
                                        navController.navigate("Profile")
                                    }
                                }
                            )

                            viewModel.dreamBookUpload(
                                enteredSchoolLifeData.dreamBookFileUri.toMultipartBody(
                                    context
                                )!!
                            )
                            dreamBookFileUpload(
                                viewModel = viewModel,
                                error = { errorState, title, msg ->
                                    isError.value = errorState
                                    errorTitle.value = title
                                    errorMsg.value = msg
                                },
                                isUnauthorized = {
                                    onClick.value = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                LoginActivity::class.java
                                            )
                                        )
                                        context.finish()
                                    }
                                },
                                isBadRequest = {
                                    onClick.value = {
                                        navController.navigate("SchoolLife")
                                    }
                                }
                            )

                            viewModel.fileUploadCompleted.collect { isComplete ->
                                if (isComplete) {
                                    viewModel.enterStudentInformation(
                                        major = enteredProfileData.major,
                                        techStack = enteredProfileData.techStack.split(",")
                                            .map { it.trim() },
                                        profileImgUrl = viewModel.getProfileImageUrl(),
                                        introduce = enteredProfileData.introduce,
                                        portfolioUrl = enteredProfileData.portfolioUrl,
                                        contactEmail = enteredProfileData.contactEmail,
                                        formOfEmployment = enteredWorkConditionData.formOfEmployment,
                                        gsmAuthenticationScore = enteredSchoolLifeData.gsmAuthenticationScore.toInt(),
                                        salary = enteredWorkConditionData.salary.toInt(),
                                        region = enteredWorkConditionData.region,
                                        languageCertificate = foreignLanguage,
                                        dreamBookFileUrl = viewModel.getDreamBookFileUrl(),
                                        militaryService = enteredMilitaryServiceData.militaryService,
                                        certificate = enteredCertificateData.certification
                                    )
                                    enterStudentInformation(
                                        viewModel = viewModel,
                                        error = { errorState, title, msg ->
                                            isError.value = errorState
                                            errorTitle.value = title
                                            errorMsg.value = msg
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

suspend fun imageFileUpload(
    viewModel: FillOutViewModel,
    error: (Boolean, String, String) -> Unit,
    isUnauthorized: () -> Unit,
    isBadRequest: () -> Unit
) {
    viewModel.imageUploadResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                viewModel.setProfileImageUrl(response.data!!.fileUrl)
            }
            is Event.Unauthorized -> {
                error(true, "토큰 만료", "다시 로그인 해주세요")
                isUnauthorized()
            }
            is Event.BadRequest -> {
                error(true, "에러", "파일이 jpg, jpeg, png, heic 가 아닙니다.")
                isBadRequest()
            }
            else -> {
                error(true, "에러", "알 수 없는 오류 발생")
            }
        }
    }
}

suspend fun dreamBookFileUpload(
    viewModel: FillOutViewModel,
    error: (Boolean, String, String) -> Unit,
    isUnauthorized: () -> Unit,
    isBadRequest: () -> Unit
) {
    viewModel.dreamBookUploadResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                viewModel.setDreamBookFileUrl(response.data!!.fileUrl)
            }
            is Event.Unauthorized -> {
                error(true, "토큰 만료", "다시 로그인 해주세요")
                isUnauthorized()
            }
            is Event.BadRequest -> {
                error(true, "에러", "파일이 hwp, hwpx 가 아닙니다.")
                isBadRequest()
            }
            else -> {
                error(true, "에러", "알 수 없는 오류 발생")
            }
        }
    }
}

suspend fun enterStudentInformation(
    viewModel: FillOutViewModel,
    error: (Boolean, String, String) -> Unit
) {
    viewModel.enterInformationResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                Log.d("Enter Student Info", response.toString())
            }
            else -> {
                error(true, "에러", "알 수 없는 오류 발생")
            }
        }
    }
}
