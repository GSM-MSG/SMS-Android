package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo

@Composable
fun ProjectsComponent(
    data: ProjectInfo,
    detailStacks: List<String>,
    isNameEmpty: Boolean,
    isIconEmpty: Boolean,
    isTechStackEmpty: Boolean,
    isDescriptionEmpty: Boolean,
    isStartDateEmpty: Boolean,
    isEndDateEmpty: Boolean,
    onCancelButtonClick: () -> Unit,
    onDetailStackSearchBarClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: (isStartDate: Boolean) -> Unit,
    onProjectProgressValueChanged: (isProgress: Boolean) -> Unit,
    onProjectNameValueChanged: (name: String) -> Unit,
    onProjectIconValueChanged: (icon: Uri) -> Unit,
    onProjectPreviewsValueChanged: (previews: List<Uri>) -> Unit,
    onProjectTechStackValueChanged: (techStacks: List<String>) -> Unit,
    onProjectDescriptionValueChanged: (description: String) -> Unit,
    onProjectKeyTaskValueChanged: (keyTask: String) -> Unit,
    onProjectRelatedLinksValueChanged: (links: List<Pair<String, String>>) -> Unit,
    onProjectItemToggleIsOpenValueChanged: (visible: Boolean) -> Unit,
    onSnackBarVisibleChanged: (text: String) -> Unit
) {
    ToggleComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        name = "프로젝트",
        contentVisible = data.isToggleOpen,
        onOpenButtonClick = { onProjectItemToggleIsOpenValueChanged(!data.isToggleOpen) },
        onCancelButtonClick = onCancelButtonClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        ) {
            ProjectNameInputComponent(
                projectName = data.name,
                isNameEmpty = isNameEmpty,
                onValueChange = onProjectNameValueChanged
            )
            ProjectIconInputComponent(
                iconImageUri = data.icon,
                isIconEmpty = isIconEmpty,
                onValueChanged = onProjectIconValueChanged
            )
            ProjectPreviewInputComponent(
                previewUriList = data.preview,
                onSnackBarVisibleChanged = onSnackBarVisibleChanged,
                onValueChanged = onProjectPreviewsValueChanged
            )
            ProjectTechStackInputComponent(
                techStack = detailStacks,
                isTechStackEmpty = isTechStackEmpty,
                onClick = onDetailStackSearchBarClick,
                onProjectTechStackValueChanged = onProjectTechStackValueChanged
            )
            ProjectDescriptionInputComponent(
                projectDescription = data.description,
                isDescriptionEmpty = isDescriptionEmpty,
                onValueChange = onProjectDescriptionValueChanged
            )
            ProjectKeyTaskInputComponent(
                projectKeyTask = data.keyTask,
                onValueChange = onProjectKeyTaskValueChanged
            )
            ProjectScheduleInputComponent(
                startDateText = data.startDate,
                endDateText = data.endDate,
                isProjectProgress = data.isProjectProgress,
                isStartDateEmpty = isStartDateEmpty,
                isEndDateEmpty = isEndDateEmpty,
                onStartDateCalendarClick = { onDateBottomSheetOpenButtonClick(true) },
                onEndDateCalendarClick = { onDateBottomSheetOpenButtonClick(false) },
                onProgressButtonClick = {
                    onProjectProgressValueChanged(!data.isProjectProgress)
                }
            )
            ProjectRelatedLinksInputComponent(
                relatedLinks = data.relatedLinkList,
                onValueChanged = onProjectRelatedLinksValueChanged
            )
        }
    }
}
