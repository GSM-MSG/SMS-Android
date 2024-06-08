package com.sms.presentation.main.ui.teacher_registration.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.ClassSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.GradeSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.PositionSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.section.TeacherRegistrationSection
import com.sms.presentation.main.ui.teacher_registration.state.Class
import com.sms.presentation.main.ui.teacher_registration.state.Grade
import com.sms.presentation.main.ui.teacher_registration.state.Position
import com.sms.presentation.main.ui.util.stringClassDataToIntClassData
import com.sms.presentation.main.ui.util.stringGradeDataToIntGradeData
import com.sms.presentation.main.viewmodel.TeacherViewModel
import kotlinx.coroutines.launch

private enum class SelectedBottomSheetSettingValue {
    Class,
    Grade,
    Position
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeacherRegistrationPageScreen(
    viewModel: TeacherViewModel
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var selectedBottomSheetSettingValue = remember {
        mutableStateOf(SelectedBottomSheetSettingValue.Position)
    }
    val scope = rememberCoroutineScope()

    val setPositionData by viewModel.positionData.collectAsState()
    val setGradeData by viewModel.gradeData.collectAsState()
    val setClassData by viewModel.classData.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
            when (selectedBottomSheetSettingValue.value) {
                SelectedBottomSheetSettingValue.Position -> {
                    PositionSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedPosition = setPositionData,
                        positionList = listOf(
                            Position.HOMEROOM.text,
                            Position.PRINCIPAL.text,
                            Position.VICE_PRINCIPAL.text,
                            Position.HEAD_OF_DEPARTMENT.text,
                            Position.BESIDES.text
                        ),
                        onSelectedPositionChange = {
                            viewModel.selectedPositionDataChange(it)
                        }
                    )
                }

                SelectedBottomSheetSettingValue.Grade -> {
                    GradeSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedGrade = setGradeData,
                        gradeList = listOf(
                            Grade.FIRST_GRADE.text,
                            Grade.SECOND_GRADE.text,
                            Grade.THIRD_GRADE.text
                        ),
                        onSelectedGradeChange = {
                            viewModel.selectedGradeDataChange(it)
                        }
                    )
                }

                SelectedBottomSheetSettingValue.Class -> {
                    ClassSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedClass = setClassData,
                        classList = listOf(
                            Class.CLASS_1.text,
                            Class.CLASS_2.text,
                            Class.CLASS_3.text,
                            Class.CLASS_4.text
                        ),
                        onSelectedClassChange = {
                            viewModel.selectedClassDataChange(it)
                        }
                    )
                }
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                TopBarComponent(
                    text = "정보입력",
                    leftIcon = {},
                    rightIcon = {}
                )
                SmsSpacer()
                TitleHeader(titleText = "프로필 *")
                TeacherRegistrationSection(
                    setPosition = setPositionData,
                    setGrade = setGradeData,
                    setClass = setClassData,
                    onClickPositionOpenButton = {
                        selectedBottomSheetSettingValue.value =
                            SelectedBottomSheetSettingValue.Position
                        scope.launch {
                            bottomSheetState.show()
                        }
                    },
                    onClickGradeOpenButton = {
                        selectedBottomSheetSettingValue.value =
                            SelectedBottomSheetSettingValue.Grade
                        scope.launch {
                            bottomSheetState.show()
                        }
                    },
                    onClickClassOpenButton = {
                        selectedBottomSheetSettingValue.value =
                            SelectedBottomSheetSettingValue.Class
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp)
            ) {
                SmsRoundedButton(
                    text = "완료",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    when (viewModel.positionData.value) {
                        "그 외 선생님" -> {
                            viewModel.common()
                        }

                        "교장선생님" -> {
                            viewModel.principal()
                        }

                        "교감선생님" -> {
                            viewModel.vicePrincipal()
                        }

                        "부장선생님" -> {
                            viewModel.headOfDepartment()
                        }

                        "담임선생님" -> {
                            viewModel.homeroom(
                                viewModel.gradeData.value.stringGradeDataToIntGradeData(),
                                viewModel.classData.value.stringClassDataToIntClassData()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Preview
@Composable
fun TeacherRegistrationPageScreenPage() {
}