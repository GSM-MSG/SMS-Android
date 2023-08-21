package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    data: ProjectInfo,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit,
    isImageExtensionInCorrect: (Boolean) -> Unit,
    onCancelButtonClick: () -> Unit,
    onDetailStackSearchBarClick: () -> Unit,
) {
    val context = LocalContext.current
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
//                            if (isProjectStartDate.value)
//                                projectStartDate.value = "${year.value}.${month.value}"
//                            else projectEndDate.value = "${year.value}.${month.value}"
                                //TODO copy로 데이터 넘기기
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
        Spacer(modifier = Modifier.height(32.dp))
        ProjectNameInputComponent(
            projectName = data.name
        ) {
            // TODO copy로 데이터 저장
            Log.d("Projects", "Name: $it")
        }
        Spacer(modifier = Modifier.height(24.dp))
        ProjectIconInputComponent(iconImageUri = data.icon) {
            // TODO copy로 데이터 저장, 이미지 extentsion검사
            Log.d("Projects", "Icon: $it")
        }
        Spacer(modifier = Modifier.height(24.dp))
        ProjectPreviewInputComponent(previewUriList = data.preview) {
            // TODO copy로 데이터 저장, 이미지 extentsion검사
            Log.d("Projects", "Preview: ${it.joinToString()}")
        }
        Spacer(modifier = Modifier.height(24.dp))
        ProjectTechStackInputComponent(
            techStack = data.technologyOfUse,
            onClick = onDetailStackSearchBarClick
        )
        Spacer(modifier = Modifier.height(24.dp))
        ProjectKeyTaskInputComponent(projectKeyTask = data.keyTask) {
            // TODO copy로 데이터 저장
            Log.d("Projects", "KeyTask: $it")
        }
        Spacer(modifier = Modifier.height(24.dp))
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
        Spacer(modifier = Modifier.height(24.dp))
        ProjectRelatedLinksInputComponent(relatedLinks = data.relatedLinkList) {
            //TODO copy로 데이터 저장
            Log.d("Projects", "RelatedLinks: ${it.joinToString()}")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
