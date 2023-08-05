package com.sms.presentation.main.ui.fill_out_information.screen

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.toggle.ToggleComponent
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.*
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect

@Preview
@Composable
fun ProjectsScreen() {
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

    SMSTheme { colors, typography ->
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
                    ProjectTechStackInputComponent(techStack = listOf("a", "a", "a", "a")) {

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
                        onStartDateCalendarClick = {},
                        onEndDateCalendarClick = {},
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
                }
            }
        }
    }
}