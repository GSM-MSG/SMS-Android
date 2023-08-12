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
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.*
import com.sms.presentation.main.ui.fill_out_information.data.ProjectInfo
import com.sms.presentation.main.viewmodel.FillOutViewModel

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
    val isImageExtensionInCorrect = remember {
        mutableStateOf(false)
    }

    if (isImageExtensionInCorrect.value) {
        SmsDialog(widthPercent = 1f,
            title = "에러",
            msg = "이미지의 확장자가 jpg, jpeg, png, heic가 아닙니다.",
            outLineButtonText = "취소",
            normalButtonText = "확인",
            outlineButtonOnClick = { isImageExtensionInCorrect.value = false },
            normalButtonOnClick = { isImageExtensionInCorrect.value = false })
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
                }
            )
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
                    Text(
                        text = "+  추가",
                        style = typography.title2,
                        fontWeight = FontWeight.Bold,
                        color = colors.BLACK,
                        modifier = Modifier.smsClickable {
                            projectList.add(ProjectInfo())
                        }
                    )
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
                    SmsRoundedButton(
                        text = "다음", modifier = Modifier.weight(2.25f)
                    ) {
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
                        Log.d(
                            "dddd",
                            viewModel.getEnteredProjectsInformation().projects.joinToString()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}