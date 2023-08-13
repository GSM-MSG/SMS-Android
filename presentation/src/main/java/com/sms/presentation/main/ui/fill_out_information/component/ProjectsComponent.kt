package com.sms.presentation.main.ui.fill_out_information.component

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
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
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.component.toggle.ToggleComponent
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsComponent(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
    detailStack: List<String>,
    data: ProjectInfo,
    savedData: (
        name: String,
        icon: Uri,
        preview: List<Uri>,
        techOfUse: List<String>,
        keyTask: String,
        startDate: String,
        endDate: String,
        relatedLink: List<Pair<String, String>>
    ) -> Unit,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit,
    isImageExtensionInCorrect: (Boolean) -> Unit,
    onCancelButtonClick: () -> Unit,
    onDetailStackSearchBarClick: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val projectName = remember {
        mutableStateOf(data.name)
    }
    val projectIconUri = remember {
        mutableStateOf(data.icon)
    }
    val projectPreviewUriList = remember {
        mutableStateListOf(*data.preview.toTypedArray())
    }
    val projectKeyTask = remember {
        mutableStateOf(data.keyTask)
    }
    val projectStartDate = remember {
        mutableStateOf(data.startDate)
    }
    val projectEndDate = remember {
        mutableStateOf(data.endDate)
    }
    val projectRelatedLinkList = remember {
        mutableStateListOf(*data.relatedLinkList.toTypedArray())
    }
    val isImportingProjectIcons = remember {
        mutableStateOf(false)
    }
    val isProjectProgress = remember {
        mutableStateOf(false)
    }
    val isProjectStartDate = remember {
        mutableStateOf(true)
    }
    val singleSelectGalleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                    isImageExtensionInCorrect(false)
                    projectIconUri.value = uri
                } else {
                    isImageExtensionInCorrect(true)
                }
            }
        }
    val multipleSelectGalleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = 4)) { uris ->
            if (uris.isNotEmpty()) {
                if (uris.all { uri ->
                        getFileNameFromUri(
                            context,
                            uri
                        )?.isImageExtensionCorrect() == true
                    }) {
                    isImageExtensionInCorrect(false)
                    projectPreviewUriList.clear()
                    projectPreviewUriList.addAll(uris)
                } else {
                    isImageExtensionInCorrect(true)
                }
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            if (isImportingProjectIcons.value)
                singleSelectGalleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            else
                multipleSelectGalleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    savedData(
        projectName.value,
        projectIconUri.value,
        projectPreviewUriList,
        detailStack,
        projectKeyTask.value,
        projectStartDate.value,
        if (!isProjectProgress.value) projectEndDate.value else "",
        projectRelatedLinkList
    )

    bottomSheetContent(content = {
        val year = remember {
            mutableStateOf(0)
        }
        val month = remember {
            mutableStateOf(0)
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
                            coroutineScope.launch { bottomSheetState.hide() }
                            if (isProjectStartDate.value)
                                projectStartDate.value = "${year.value}.${month.value}"
                            else projectEndDate.value = "${year.value}.${month.value}"
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            SmsDatePicker(yearValue = year.value,
                monthValue = month.value,
                yearRange = 2000..2030,
                monthRange = 1..12,
                onYearValueChange = { year.value = it },
                onMonthValueChange = { month.value = it })
        }
    })

    ToggleComponent(name = "프로젝트", onCancelButtonClick = onCancelButtonClick) {
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
        ProjectTechStackInputComponent(techStack = detailStack, onClick = onDetailStackSearchBarClick)
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
