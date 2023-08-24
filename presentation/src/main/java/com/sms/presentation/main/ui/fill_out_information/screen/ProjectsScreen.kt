package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
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
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsBottomButtonComponent
import com.sms.presentation.main.ui.fill_out_information.component.projects.ProjectsComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect

@Composable
fun ProjectsScreen(
    navController: NavController,
    projects: List<ProjectInfo>,
    onAddButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onCancelButtonClick: (index: Int) -> Unit,
    onDateBottomSheetOpenButtonClick: (index: Int, isStartDate: Boolean) -> Unit,
    onSnackBarVisibleChanged: () -> Unit,
    onProjectItemToggleIsOpenValueChanged: (index: Int, value: Boolean) -> Unit,
    onProjectNameValueChanged: (index: Int, value: String) -> Unit,
    onProjectIconValueChanged: (index: Int, value: Uri) -> Unit,
    onProjectPreviewsValueChanged: (index: Int, value: List<Uri>) -> Unit,
    onProjectTechStackValueChanged: (index: Int, list: List<String>) -> Unit,
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

    LazyColumn {
        item {
            SmsSpacer()
        }
        itemsIndexed(projects) { index, item ->
            ProjectsComponent(
                data = item,
                onProjectNameValueChanged = { onProjectNameValueChanged(index, it) },
                onProjectIconValueChanged = { uri ->
                    if (getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()) {
                        isImageExtensionInCorrect.value = false
                        onProjectIconValueChanged(index, uri)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectPreviewsValueChanged = { uris ->
                    if (uris.all { uri -> getFileNameFromUri(context, uri)?.isImageExtensionCorrect() == true }) {
                        isImageExtensionInCorrect.value = false
                        onProjectPreviewsValueChanged(index, uris)
                    } else {
                        isImageExtensionInCorrect.value = true
                    }
                },
                onProjectTechStackValueChanged = {
                    onProjectTechStackValueChanged(index, it)
                },
                onProjectKeyTaskValueChanged = { onProjectKeyTaskValueChanged(index, it) },
                onProjectRelatedLinksValueChanged = { onProjectRelatedLinksValueChanged(index, it) },
                onDateBottomSheetOpenButtonClick = { onDateBottomSheetOpenButtonClick(index, it) },
                onProjectItemToggleIsOpenValueChanged = { onProjectItemToggleIsOpenValueChanged(index, it) },
                onSnackBarVisibleChanged = onSnackBarVisibleChanged,
                onCancelButtonClick = { onCancelButtonClick(index) },
                onDetailStackSearchBarClick = { navController.navigate("Search/Project$index") }
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
                onNextButtonClick = onNextButtonClick
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}