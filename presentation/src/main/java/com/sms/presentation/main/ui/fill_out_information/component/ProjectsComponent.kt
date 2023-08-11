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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsComponent(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStack: List<String>,
    bottomSheetState: ModalBottomSheetState,
    data: ProjectInfo,
    savedData: (
        name: String,
        icon: Uri,
        preview: List<Uri>,
        keyTask: String,
        startDate: String,
        endDate: String,
        relatedLink: List<Pair<String, String>>
    ) -> Unit,
    isImageExtensionInCorrect: (Boolean) -> Unit,
    onCancelButtonClick: () -> Unit
) {
    val context = LocalContext.current
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
        projectKeyTask.value,
        projectStartDate.value,
        projectEndDate.value,
        projectRelatedLinkList
    )

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
        ProjectTechStackInputComponent(techStack = detailStack) {
            navController.navigate("Search")
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
            },
            onEndDateCalendarClick = {
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
