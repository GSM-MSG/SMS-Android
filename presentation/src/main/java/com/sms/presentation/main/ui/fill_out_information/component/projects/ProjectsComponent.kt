package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.component.toggle.ToggleComponent
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.projects.*
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsComponent(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit,
    data: ProjectInfo,
    onStartDateValueChanged: (value: String) -> Unit,
    onEndDateValueChanged: (value: String) -> Unit,
    onProjectNameValueChanged: (value: String) -> Unit,
    onProjectIconValueChanged: (value: Uri) -> Unit,
    onProjectPreviewsValueChanged: (value: List<Uri>) -> Unit,
    onProjectKeyTaskValueChanged: (value: String) -> Unit,
    onProjectRelatedLinksValueChanged: (value: List<Pair<String, String>>) -> Unit,
    onSnackBarVisibleChanged: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onDetailStackSearchBarClick: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val isProjectProgress = remember {
        mutableStateOf(false)
    }
    val isProjectStartDate = remember {
        mutableStateOf(true)
    }

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
                                onStartDateValueChanged("${year.value}.${month.value}")
                            else
                                onEndDateValueChanged("${year.value}.${month.value}")
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
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
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
            ProjectRelatedLinksInputComponent(
                relatedLinks = data.relatedLinkList,
                onValueChanged = onProjectRelatedLinksValueChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
