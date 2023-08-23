package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.main.ui.fill_out_information.component.projects.AddProjectButton
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStackList: Map<String, List<String>>,
    startDateMap: Map<Int, String>,
    endDateMap: Map<Int, String>,
    onSnackBarVisibleChanged: () -> Unit,
    onDateBottomSheetOpenButtonClick: (isStartDate: Boolean, idx: Int) -> Unit
) {
    val context = LocalContext.current
    val data = viewModel.getEnteredProjectsInformation()
    val projectList = remember {
        mutableStateListOf(*data.projects.toTypedArray())
    }
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }

    if (isImageExtensionInCorrect.value) {
        SmsDialog(
            widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            importantButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            importantButtonOnClick = { isImageExtensionInCorrect.value = false }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(projectList) { idx, item ->
            projectList[idx] = projectList[idx].copy(
                technologyOfUse = detailStackList["Project$idx"] ?: emptyList(),
                startDate = startDateMap[idx] ?: "",
                endDate = endDateMap[idx] ?: ""
            )


            ProjectsComponent(
                navController = navController,
                data = item,
                onProjectNameValueChanged = { projectList[idx] = projectList[idx].copy(name = it) },
                onProjectIconValueChanged = { uri ->
                    if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                        isImageExtensionInCorrect.value = false
                        projectList[idx] = projectList[idx].copy(icon = uri)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectPreviewsValueChanged = { uris ->
                    if (uris.all { uri -> getFileNameFromUri(context, uri)?.isImageExtensionCorrect() == true }) {
                        isImageExtensionInCorrect.value = false
                        projectList[idx] = projectList[idx].copy(preview = uris)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectKeyTaskValueChanged = {
                    projectList[idx] = projectList[idx].copy(keyTask = it)
                },
                onProjectRelatedLinksValueChanged = {
                    projectList[idx] = projectList[idx].copy(relatedLinkList = it)
                },
                onDateBottomSheetOpenButtonClick = {
                    onDateBottomSheetOpenButtonClick(it, idx)
                },
                onSnackBarVisibleChanged = onSnackBarVisibleChanged,
                onCancelButtonClick = { projectList.removeAt(idx) },
                onDetailStackSearchBarClick = { navController.navigate("Search/Project$idx") }
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            AddProjectButton {
                projectList.add(ProjectInfo())
            }
        }
        item {
            Spacer(modifier = Modifier.height(52.dp))
            ProjectsBottomButtonComponent(
                onPreviousButtonClick = { navController.popBackStack() },
                onNextButtonClick = {
                    viewModel.setEnteredProjectsInformation(
                        projectList.filter { project ->
                            project.name.isNotEmpty() ||
                            project.icon != Uri.EMPTY ||
                            project.keyTask.isNotEmpty() ||
                            project.preview.isNotEmpty() ||
                            project.endDate.isNotEmpty() ||
                            project.startDate.isNotEmpty() ||
                            project.technologyOfUse.isNotEmpty() ||
                            project.relatedLinkList.first() != Pair("", "")
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}