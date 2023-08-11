package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.picker.SmsDatePicker
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.*
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.viewmodel.FillOutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: FillOutViewModel,
    detailStack: List<String>,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable (content: @Composable ColumnScope.() -> Unit) -> Unit
) {
    val data = viewModel.getEnteredProjectsInformation()
    val projectList = remember {
        mutableStateListOf(*data.projects.toTypedArray())
    }
    val coroutineScope = rememberCoroutineScope()
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }

    Log.d("ddd", data.projects.joinToString())

    if (isImageExtensionInCorrect.value) {
        SmsDialog(widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            normalButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            normalButtonOnClick = { isImageExtensionInCorrect.value = false })
    }

    bottomSheetContent(content = {
        val year = remember {
            mutableStateOf(0)
        }
        val month = remember {
            mutableStateOf(0)
        }
        val yearRange = remember {
            mutableStateOf(2000..2030)
        }
        val monthRange = remember {
            mutableStateOf(1..12)
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
                Text(text = "완료",
                    style = typography.body2,
                    color = colors.P2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .smsClickable {
//                                if (isProjectStartDate.value) projectStartDate.value =
//                                    "${year.value}.${month.value}"
//                                else projectEndDate.value =
//                                    "${year.value}.${month.value}"
                            coroutineScope.launch { bottomSheetState.hide() }
                        })
            }
            Spacer(modifier = Modifier.height(16.dp))
            SmsDatePicker(yearValue = year.value,
                monthValue = month.value,
                yearRange = yearRange.value,
                monthRange = monthRange.value,
                onYearValueChange = { year.value = it },
                onMonthValueChange = { month.value = it })
        }
    })
    LazyColumn {
        itemsIndexed(projectList) { idx, item ->
            ProjectsComponent(navController = navController,
                viewModel = viewModel,
                detailStack = detailStack,
                bottomSheetState = bottomSheetState,
                data = item,
                savedData = { name, icon, preview, keyTask, startDate, endDate, relatedLink ->
                    projectList[idx] = ProjectInfo(
                        name = name,
                        icon = icon,
                        preview = preview,
                        keyTask = keyTask,
                        startDate = startDate,
                        endDate = endDate,
                        relatedLinkList = relatedLink,
                        technologyOfUse = detailStack
                    )
                },
                isImageExtensionInCorrect = {
                    isImageExtensionInCorrect.value = it
                },
                onCancelButtonClick = {
                    projectList.removeAt(idx)
                })
        }
        item {
            SMSTheme { colors, typography ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "+  추가",
                        style = typography.title2,
                        fontWeight = FontWeight.Bold,
                        color = colors.BLACK,
                        modifier = Modifier.smsClickable {
                            projectList.add(
                                ProjectInfo(
                                    name = "",
                                    icon = Uri.EMPTY,
                                    preview = emptyList(),
                                    technologyOfUse = emptyList(),
                                    keyTask = "",
                                    startDate = "",
                                    endDate = "",
                                    relatedLinkList = emptyList()
                                )
                            )
                        })
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(52.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    SmsRoundedButton(
                        text = "이전", modifier = Modifier.weight(1f), state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(text = "다음", modifier = Modifier.weight(2.25f)) {
                        viewModel.setEnteredProjectsInformation(projectList.filter {
                            it.name != "" || it.icon != Uri.EMPTY || it.keyTask != "" || it.preview.isNotEmpty() || it.endDate != "" || it.startDate != "" || it.technologyOfUse.isNotEmpty() || it.relatedLinkList.isNotEmpty()
                        })
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}