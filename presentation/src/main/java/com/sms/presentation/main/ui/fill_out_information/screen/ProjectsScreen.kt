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
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.viewmodel.FillOutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStack: List<String>,
    bottomSheetState: ModalBottomSheetState,
    onDetailStackSearchBarClick: (idx: Int) -> Unit,
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
            ProjectsComponent(
                navController = navController,
                data = item,
                detailStack = detailStack,
                savedData = { name, icon, preview, techOfUse, keyTask, startDate, endDate, relatedLink ->
                    projectList[idx] = ProjectInfo(
                        name = name,
                        icon = icon,
                        preview = preview,
                        technologyOfUse = techOfUse,
                        keyTask = keyTask,
                        startDate = startDate,
                        endDate = endDate,
                        relatedLinkList = relatedLink
                    )
                },
                bottomSheetState = bottomSheetState,
                bottomSheetContent = bottomSheetContent,
                isImageExtensionInCorrect = {
                    isImageExtensionInCorrect.value = it
                },
                onCancelButtonClick = {
                    projectList.removeAt(idx)
                },
                onDetailStackSearchBarClick = {
                    onDetailStackSearchBarClick(idx)
                }
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