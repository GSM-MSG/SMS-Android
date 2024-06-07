package com.sms.presentation.main.ui.teacher_registration.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopNavigation
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.ClassSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.GradeSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.bottomsheet.PositionSelectorBottomSheet
import com.sms.presentation.main.ui.teacher_registration.section.TeacherRegistrationSection
import com.sms.presentation.main.ui.teacher_registration.state.Class
import com.sms.presentation.main.ui.teacher_registration.state.Grade
import com.sms.presentation.main.ui.teacher_registration.state.Position

private enum class BottomSheetValues {
    Class,
    Grade,
    Position
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TeacherRegistrationPageScreen(){
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val bottomSheetValues = remember {
        mutableStateOf(BottomSheetValues.Position)
    }


    val positionData = "담임선생님"
    val gradeData = ""
    val classData = ""

    ModalBottomSheetLayout(
        sheetContent = {
            when (bottomSheetValues.value) {
                BottomSheetValues.Position -> {
                    PositionSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedPosition = positionData,
                        positionList = listOf(
                            Position.HOMEROOM,
                            Position.PRINCIPAL,
                            Position.VICE_PRINCIPAL,
                            Position.HEAD_OF_DEPARTMENT,
                            Position.BESIDES
                        ),
                        onSelectedPositionChange = {}
                    )
                }
                BottomSheetValues.Grade -> {
                    GradeSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedGrade = gradeData,
                        gradeList = listOf(
                            Grade.FIRST_GRADE,
                            Grade.SECOND_GRADE,
                            Grade.THIRD_GRADE
                        ),
                        onSelectedGradeChange = {}
                    )
                }
                BottomSheetValues.Class -> {
                    ClassSelectorBottomSheet(
                        bottomSheetState = bottomSheetState,
                        selectedClass = classData,
                        classList = listOf(
                            Class.CLASS_1,
                            Class.CLASS_2,
                            Class.CLASS_3,
                            Class.CLASS_4
                        ),
                        onSelectedClassChange = {}
                    )
                }
            }
        }
    ) {
        Box {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ){
                TopNavigation(
                    text = "정보입력",
                    leftIcon = {},
                    rightIcon = {}
                )
                SmsSpacer()
                TitleHeader(titleText = "프로필 *")
                TeacherRegistrationSection(
                    setPosition = positionData,
                    setGrade = gradeData,
                    setClass = classData,
                    onClickPositionOpenButton = {},
                    onClickGradeOpenButton = {},
                    onClickClassOpenButton = {}
                )
            }
            Column (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp)
            ) {
                SmsRoundedButton(
                    text = "완료",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {

                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Preview
@Composable
fun TeacherRegistrationPageScreenPage(){
    TeacherRegistrationPageScreen()
}