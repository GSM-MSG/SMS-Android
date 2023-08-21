package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.main.ui.fill_out_information.component.*
import com.sms.presentation.main.ui.fill_out_information.component.projects.AddProjectButton
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStackList: Map<String, List<String>>,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit
) {
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
            normalButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            normalButtonOnClick = { isImageExtensionInCorrect.value = false }
        )
    }

    LazyColumn {
        itemsIndexed(projectList) { idx, item ->
            projectList[idx] = projectList[idx].copy(
                technologyOfUse = detailStackList["Project$idx"] ?: emptyList()
            )

            ProjectsComponent(
                navController = navController,
                bottomSheetState = bottomSheetState,
                bottomSheetContent = bottomSheetContent,
                data = item,
                onStartDateValueChanged = {
                    projectList[idx] = projectList[idx].copy(startDate = it)
                },
                onEndDateValueChanged = { projectList[idx] = projectList[idx].copy(endDate = it) },
                onProjectNameValueChanged = { projectList[idx] = projectList[idx].copy(name = it) },
                onProjectIconValueChanged = { projectList[idx] = projectList[idx].copy(icon = it) },
                onProjectPreviewsValueChanged = { projectList[idx] = projectList[idx].copy(preview = it) },
                onProjectKeyTaskValueChanged = { projectList[idx] = projectList[idx].copy(keyTask = it) },
                onProjectRelatedLinksValueChanged = { projectList[idx] = projectList[idx].copy(relatedLinkList = it) },
                onCancelButtonClick = { projectList.removeAt(idx) },
                onDetailStackSearchBarClick = { navController.navigate("Search/Project$idx") }
            )
        }
        item {
            AddProjectButton {
                projectList.add(ProjectInfo())
            }
        }
        item {
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
        }
    }
}