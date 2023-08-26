package com.sms.presentation.main.ui.fill_out_information.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ListAddButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.award.AwardComponent
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.util.isEmailRegularExpression
import com.sms.presentation.main.ui.util.isUrlRegularExpression
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.util.Event


@Composable
fun AwardScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    awardDateMap: Map<Int, String>,
    onPreviousButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: (index: Int) -> Unit
) {
    val enteredProfileData = viewModel.getEnteredProfileInformation()
    val enteredSchoolLifeData = viewModel.getEnteredSchoolLifeInformation()
    val enteredWorkConditionData = viewModel.getEnteredWorkConditionInformation()
    val enteredMilitaryServiceData = viewModel.getEnteredMilitaryServiceInformation()
    val enteredCertificateData = viewModel.getEnteredCertification()
    val enteredForeignLanguagesData = viewModel.getEnteredForeignLanguagesInformation()

    val awardList = remember {
        mutableStateListOf(AwardData("", "", "", isToggleOpen = true))
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
                    /* TODO kimhyunseung : 데이터 모아서 정보기입 요청 보내기 */
                }
            )
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