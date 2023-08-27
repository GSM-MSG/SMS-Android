package com.sms.presentation.main.ui.fill_out_information.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.msg.sms.design.component.button.ListAddButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.domain.model.student.request.*
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.main.MainActivity
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AwardScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    lifecycleScope: CoroutineScope,
    awardDateMap: Map<Int, String>,
    onPreviousButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: (index: Int) -> Unit
) {
    val context = LocalContext.current as FillOutInformationActivity
    val enteredProfileData = viewModel.getEnteredProfileInformation()
    val enteredSchoolLifeData = viewModel.getEnteredSchoolLifeInformation()
    val enteredWorkConditionData = viewModel.getEnteredWorkConditionInformation()
    val enteredMilitaryServiceData = viewModel.getEnteredMilitaryServiceInformation()
    val enteredCertificateData = viewModel.getEnteredCertification()
    val enteredForeignLanguagesData = viewModel.getEnteredForeignLanguagesInformation()
    val enteredProjectsData = viewModel.getEnteredProjectsInformation()
    var profileUrl = ""
    val projectsIcons = mutableMapOf<Int, String>()
    val projectsPreviews = mutableMapOf<Int, List<String>>()

    val awardList = remember {
        mutableStateListOf(AwardData("", "", "", isToggleOpen = true))
    }

    val dialogVisile = remember {
        mutableStateOf(false)
    }
    val dialogTitle = remember {
        mutableStateOf("")
    }
    val dialogText = remember {
        mutableStateOf("")
    }

    if (dialogVisile.value) {
        SmsDialog(
            title = dialogTitle.value,
            msg = dialogText.value,
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = { dialogVisile.value = false },
            importantButtonOnClick = { dialogVisile.value = false }
        )
    }

    LazyColumn {
        item {
            SmsSpacer()
        }
        itemsIndexed(awardList) { index, item ->
            awardList[index] = awardList[index].copy(date = awardDateMap[index] ?: "")

            AwardComponent(
                data = item,
                onDateBottomSheetOpenButtonClick = {
                    onDateBottomSheetOpenButtonClick(index)
                },
                onNameValueChange = {
                    awardList[index] = awardList[index].copy(name = it)
                },
                onTypeValueChange = {
                    awardList[index] = awardList[index].copy(type = it)
                },
                onCancelButtonClick = {
                    awardList.removeAt(index)
                }
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 52.dp),
                horizontalAlignment = Alignment.End
            ) {
                ListAddButton {
                    awardList.add(AwardData("", "", ""))
                }
            }
        }
        item {
            AwardBottomButtonComponent(
                onPreviousButtonClick = onPreviousButtonClick,
                onCompleteButtonClick = {
                    lifecycleScope.launch {
                        viewModel.imageUpload(
                            enteredProfileData.profileImageUri.toMultipartBody(
                                context
                            )!!
                        ) { url ->
                            profileUrl = url
                        }

                        enteredProjectsData.projects.forEachIndexed { index, projectInfo ->
                            viewModel.imageUpload(projectInfo.icon.toMultipartBody(context)!!) { url ->
                                projectsIcons[index] = url
                            }

                            projectInfo.preview.forEach {
                                val urlList = arrayListOf<String>()

                                viewModel.imageUpload(it.toMultipartBody(context)!!) { url ->
                                    urlList.add(url)
                                    projectsPreviews[index] = urlList
                                }
                            }
                            if (index == enteredProjectsData.projects.lastIndex) {
                                viewModel.enterStudentInformation(
                                    major = if (enteredProfileData.major == "직접입력") enteredProfileData.enteredMajor else enteredProfileData.major,
                                    techStack = enteredProfileData.techStack.map { it.trim() },
                                    profileImgUrl = profileUrl,
                                    introduce = enteredProfileData.introduce,
                                    portfolioUrl = enteredProfileData.portfolioUrl,
                                    contactEmail = enteredProfileData.contactEmail,
                                    formOfEmployment = enteredWorkConditionData.formOfEmployment.toEnum(),
                                    gsmAuthenticationScore = enteredSchoolLifeData.gsmAuthenticationScore.toInt(),
                                    salary = enteredWorkConditionData.salary.toInt(),
                                    region = enteredWorkConditionData.regions,
                                    languageCertificate = enteredForeignLanguagesData.foreignLanguages.map {
                                        CertificateInformationModel(
                                            languageCertificateName = it.languageCertificateName,
                                            score = it.score
                                        )
                                    },
                                    militaryService = enteredMilitaryServiceData.militaryService.toEnum(),
                                    certificate = enteredCertificateData.certifications,
                                    projects = enteredProjectsData.projects.mapIndexed { idx, data ->
                                        ProjectModel(
                                            name = data.name,
                                            icon = projectsIcons[idx] ?: "",
                                            previewImages = projectsPreviews[idx] ?: emptyList(),
                                            description = data.description,
                                            links = data.relatedLinkList.map { relatedLink ->
                                                ProjectRelatedLinkModel(
                                                    name = relatedLink.first,
                                                    url = relatedLink.second
                                                )
                                            },
                                            techStacks = data.technologyOfUse,
                                            myActivity = data.keyTask,
                                            inProgress = ProjectDateModel(
                                                start = data.startDate,
                                                end = data.endDate
                                            )
                                        )
                                    },
                                    award = awardList.map {
                                        PrizeModel(
                                            name = it.name,
                                            type = it.type,
                                            date = it.date
                                        )
                                    }
                                )
                            }
                        }
                    }

                    lifecycleScope.launch {
                        enterStudentInformation(
                            viewModel = viewModel,
                            onSuccess = {
                                context.startActivity(Intent(context, MainActivity::class.java))
                                context.finish()
                            },
                            dialog = { visible, title, text ->
                                dialogVisile.value = visible
                                dialogTitle.value = title
                                dialogText.value = text
                            }
                        )
                    }
                }
            )
        }
    }
}

suspend fun enterStudentInformation(
    viewModel: FillOutViewModel,
    dialog: (visible: Boolean, title: String, text: String) -> Unit,
    onSuccess: () -> Unit,
) {
    viewModel.enterInformationResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                dialog(true, "성공", "정보기입을 완료하였습니다.")
                onSuccess()
            }

            is Event.BadRequest -> {
                dialog(true, "실패", "배드 리퀘스가")
            }

            is Event.Conflict -> {
                dialog(true, "실패", "이미 존재하는 유저 입니다.")
            }

            is Event.Loading -> {}
            else -> {
                dialog(true, "실패", "알 수 없는 에러.")
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