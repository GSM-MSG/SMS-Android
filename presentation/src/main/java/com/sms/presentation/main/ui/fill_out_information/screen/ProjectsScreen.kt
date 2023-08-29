package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ListAddButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.fill_out_information.data.ProjectRequiredDataInfo
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect

@Composable
fun ProjectsScreen(
    navController: NavController,
    listState: LazyListState,
    projects: List<ProjectInfo>,
    detailStacks: List<List<String>>,
    projectRequiredDataInfoList: List<ProjectRequiredDataInfo>,
    onAddButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onCancelButtonClick: (index: Int) -> Unit,
    onDateBottomSheetOpenButtonClick: (index: Int, isStartDate: Boolean) -> Unit,
    onDetailStackSearchBarClick: (index: Int) -> Unit,
    onSnackBarVisibleChanged: (text: String) -> Unit,
    onProjectItemToggleIsOpenValueChanged: (index: Int, value: Boolean) -> Unit,
    onProjectProgressValueChanged: (index: Int, value: Boolean) -> Unit,
    onProjectNameValueChanged: (index: Int, value: String) -> Unit,
    onProjectIconValueChanged: (index: Int, value: Uri) -> Unit,
    onProjectPreviewsValueChanged: (index: Int, value: List<Uri>) -> Unit,
    onProjectTechStackValueChanged: (index: Int, list: List<String>) -> Unit,
    onProjectDescriptionValueChanged: (index: Int, value: String) -> Unit,
    onProjectKeyTaskValueChanged: (index: Int, value: String) -> Unit,
    onProjectRelatedLinksValueChanged: (index: Int, value: List<Pair<String, String>>) -> Unit,
) {
    val context = LocalContext.current
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

    LazyColumn(state = listState) {
        item {
            SmsSpacer()
        }
        itemsIndexed(projects) { index, item ->
            LaunchedEffect("SnackBar") {
                if (detailStacks[index].size > 20) {
                    onSnackBarVisibleChanged("스택 갯수를 초과하여 ${detailStacks[index].size - 20}개가 제외되었어요.")
                }
            }

            ProjectsComponent(
                data = item,
                detailStacks = if (detailStacks[index].size > 20) detailStacks[index].subList(0, 20) else detailStacks[index],
                isNameEmpty = projectRequiredDataInfoList[index].isNameEmpty,
                isIconEmpty = projectRequiredDataInfoList[index].isIconEmpty,
                isTechStackEmpty = projectRequiredDataInfoList[index].isTechStackEmpty,
                isDescriptionEmpty = projectRequiredDataInfoList[index].isDescriptionEmpty,
                isStartDateEmpty = projectRequiredDataInfoList[index].isStartDateEmpty,
                isEndDateEmpty = projectRequiredDataInfoList[index].isEndDateEmpty,
                onCancelButtonClick = {
                    onCancelButtonClick(index)
                },
                onDetailStackSearchBarClick = { onDetailStackSearchBarClick(index) },
                onProjectNameValueChanged = { name ->
                    onProjectNameValueChanged(index, name)
                },
                onProjectIconValueChanged = { icon ->
                    if (getFileNameFromUri(context, icon)!!.isImageExtensionCorrect()) {
                        isImageExtensionInCorrect.value = false
                        onProjectIconValueChanged(index, icon)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectPreviewsValueChanged = { previews ->
                    if (previews.all { uri -> getFileNameFromUri(context, uri)?.isImageExtensionCorrect() == true }) {
                        isImageExtensionInCorrect.value = false
                        onProjectPreviewsValueChanged(index, previews)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectTechStackValueChanged = { techStacks ->
                    onProjectTechStackValueChanged(index, techStacks)
                },
                onProjectDescriptionValueChanged = { description ->
                    onProjectDescriptionValueChanged(index, description)
                },
                onProjectKeyTaskValueChanged = { keyTask ->
                    onProjectKeyTaskValueChanged(index, keyTask)
                },
                onProjectRelatedLinksValueChanged = { links ->
                    onProjectRelatedLinksValueChanged(index, links)
                },
                onDateBottomSheetOpenButtonClick = { isStartDate ->
                    onDateBottomSheetOpenButtonClick(index, isStartDate)
                },
                onProjectItemToggleIsOpenValueChanged = { visible ->
                    onProjectItemToggleIsOpenValueChanged(index, visible)
                },
                onProjectProgressValueChanged = { isProgress ->
                    onProjectProgressValueChanged(index, isProgress)
                },
                onSnackBarVisibleChanged = onSnackBarVisibleChanged
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 52.dp),
                horizontalAlignment = Alignment.End
            ) {
                ListAddButton(onClick = onAddButtonClick)
            }
        }
        item {
            ProjectsBottomButtonComponent(
                onPreviousButtonClick = { navController.popBackStack() },
                onNextButtonClick = {
                    onNextButtonClick()
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}