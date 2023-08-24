package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo

@Composable
fun ProjectsComponent(
    navController: NavController,
    data: ProjectInfo,
    onCancelButtonClick: () -> Unit,
    onSnackBarVisibleChanged: () -> Unit,
    onDetailStackSearchBarClick: () -> Unit,
    onProjectNameValueChanged: (value: String) -> Unit,
    onProjectIconValueChanged: (value: Uri) -> Unit,
    onProjectPreviewsValueChanged: (value: List<Uri>) -> Unit,
    onProjectKeyTaskValueChanged: (value: String) -> Unit,
    onProjectRelatedLinksValueChanged: (value: List<Pair<String, String>>) -> Unit,
    onDateBottomSheetOpenButtonClick: (isStartDate: Boolean) -> Unit,
) {
    val isProjectProgress = remember {
        mutableStateOf(false)
    }
    val contentVisible = remember {
        mutableStateOf(data.isExpand)
    }

    ToggleComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        name = "프로젝트",
        contentVisible = contentVisible.value,
        onOpenButtonClick = { contentVisible.value = !contentVisible.value },
        onCancelButtonClick = onCancelButtonClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        ) {
            ProjectNameInputComponent(
                projectName = data.name,
                onValueChange = onProjectNameValueChanged
            )
            ProjectIconInputComponent(
                iconImageUri = data.icon,
                onValueChanged = onProjectIconValueChanged
            )
            ProjectPreviewInputComponent(
                previewUriList = data.preview,
                onSnackBarVisibleChanged = onSnackBarVisibleChanged,
                onValueChanged = onProjectPreviewsValueChanged
            )
            ProjectTechStackInputComponent(
                techStack = data.technologyOfUse,
                onClick = onDetailStackSearchBarClick
            )
            ProjectKeyTaskInputComponent(
                projectKeyTask = data.keyTask,
                onValueChange = onProjectKeyTaskValueChanged
            )
            ProjectScheduleInputComponent(
                startDateText = data.startDate,
                endDateText = data.endDate,
                isProjectProgress = isProjectProgress.value,
                onStartDateCalendarClick = { onDateBottomSheetOpenButtonClick(true) },
                onEndDateCalendarClick = { onDateBottomSheetOpenButtonClick(false) },
                onProgressButtonClick = {
                    isProjectProgress.value = !isProjectProgress.value
                }
            )
            ProjectRelatedLinksInputComponent(
                relatedLinks = data.relatedLinkList,
                onValueChanged = onProjectRelatedLinksValueChanged
            )
        }
    }
}
