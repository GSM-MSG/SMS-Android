package com.sms.presentation.main.ui.fill_out_information.screen

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.component.toggle.ToggleComponent
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.*
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import com.sms.presentation.main.viewmodel.FillOutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val projectName = remember {
        mutableStateOf("")
    }
    val projectIconUri = remember {
        mutableStateOf(Uri.EMPTY)
    }
    val projectPreviewUriList = remember {
        mutableStateListOf<Uri>()
    }
    val projectKeyTask = remember {
        mutableStateOf("")
    }
    val projectStartDate = remember {
        mutableStateOf("")
    }
    val projectEndDate = remember {
        mutableStateOf("")
    }
    val projectRelatedLinkList = remember {
        mutableStateListOf(Pair("", ""))
    }
    val isProjectProgress = remember {
        mutableStateOf(false)
    }
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }
    val isImportingProjectIcons = remember {
        mutableStateOf(true)
    }
    val isProjectStartDate = remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                    isImageExtensionInCorrect.value = false
                    if (isImportingProjectIcons.value) {
                        projectIconUri.value = uri
                    } else {
                        if (projectPreviewUriList.size < 4) projectPreviewUriList.add(uri)
                    }
                } else {
                    isImageExtensionInCorrect.value = true
                }
            }
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) galleryLauncher.launch("image/*")
    }

    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

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

    bottomSheetContent(
        content = {
            val year = remember {
                mutableStateOf(0)
            }
            val month = remember {
                mutableStateOf(0)
            }
            val yearRange = remember {
                mutableStateOf(2000..2030)
            }
            val monthRange = remember {
                mutableStateOf(1..12)
            }

            SMSTheme { colors, typography ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "날짜 선택",
                        style = typography.title2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "완료",
                        style = typography.body2,
                        color = colors.P2,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .smsClickable {
                                if (isProjectStartDate.value) projectStartDate.value =
                                    "${year.value}.${month.value}"
                                else projectEndDate.value =
                                    "${year.value}.${month.value}"
                                coroutineScope.launch { bottomSheetState.hide() }
                            }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                SmsDatePicker(
                    yearValue = year.value,
                    monthValue = month.value,
                    yearRange = yearRange.value,
                    monthRange = monthRange.value,
                    onYearValueChange = { year.value = it },
                    onMonthValueChange = { month.value = it }
                )
            }
        }
    )
    LazyColumn {
        item {
            ToggleComponent(name = "프로젝트", onCancelButtonClick = {}) {
                Spacer(modifier = Modifier.height(32.dp))
                ProjectNameInputComponent(
                    text = projectName.value,
                    onButtonClick = { projectName.value = "" }
                ) {
                    projectName.value = it
                }
                Spacer(modifier = Modifier.height(24.dp))
                ProjectIconInputComponent(iconImageUri = projectIconUri.value) {
                    isImportingProjectIcons.value = true
                    permissionLauncher.launch(permission)
                }
                Spacer(modifier = Modifier.height(24.dp))
                ProjectPreviewInputComponent(
                    previewUriList = projectPreviewUriList,
                    deletedIndex = { projectPreviewUriList.removeAt(it) }
                ) {
                    isImportingProjectIcons.value = false
                    permissionLauncher.launch(permission)
                }
                Spacer(modifier = Modifier.height(24.dp))
                ProjectTechStackInputComponent(techStack = listOf()) {

                }
                Spacer(modifier = Modifier.height(24.dp))
                ProjectKeyTaskInputComponent(
                    text = projectKeyTask.value,
                    onButtonClick = { projectKeyTask.value = "" }
                ) {
                    projectKeyTask.value = it
                }
                Spacer(modifier = Modifier.height(24.dp))
                ProjectScheduleInputComponent(
                    startDateText = projectStartDate.value,
                    endDateText = projectEndDate.value,
                    isProjectProgress = isProjectProgress.value,
                    onStartDateCalendarClick = {
                        isProjectStartDate.value = true
                        coroutineScope.launch { bottomSheetState.show() }
                    },
                    onEndDateCalendarClick = {
                        isProjectStartDate.value = false
                        coroutineScope.launch { bottomSheetState.show() }
                    },
                    onProgressButtonClick = {
                        isProjectProgress.value = !isProjectProgress.value
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                ProjectRelatedLinksInputComponent(
                    relatedLinks = projectRelatedLinkList,
                    onAddButtonClick = { projectRelatedLinkList.add(Pair("", "")) },
                    onDeleteButtonClick = { projectRelatedLinkList.removeAt(it) },
                    onValueChange = { idx, name, link ->
                        projectRelatedLinkList[idx] = Pair(name, link)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item {
            SMSTheme { colors, typography ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "+  추가",
                        style = typography.title2,
                        fontWeight = FontWeight.Bold,
                        color = colors.BLACK,
                        modifier = Modifier.smsClickable {
                            //TODO (Kimhyunseung) - 데이터 저장 구현하면서 같이 구현 예정 (프로젝트 추가)
                        }
                    )
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(52.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    SmsRoundedButton(
                        text = "이전",
                        modifier = Modifier.weight(1f),
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(text = "다음", modifier = Modifier.weight(2.25f)) {
                        //TODO (Kimhyunseung) - 데이터 저장
                        //TODO (Kimhyunseung) - 수상경력 완성 후 navigate 로직 작성
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}