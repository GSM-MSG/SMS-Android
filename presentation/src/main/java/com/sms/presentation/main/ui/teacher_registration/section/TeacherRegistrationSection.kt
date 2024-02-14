package com.sms.presentation.main.ui.teacher_registration.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.teacher_registration.component.ClassSelectorComponent
import com.sms.presentation.main.ui.teacher_registration.component.GradeSelectorComponent
import com.sms.presentation.main.ui.teacher_registration.component.PositionTypeComponent

@Composable
fun TeacherRegistrationSection(
    positionData: String,
    setPosition: String,
    setGrade: String,
    setClass: String,
    onClickPositionOpenButton: () -> Unit,
    onClickGradeOpenButton: () -> Unit,
    onClickClassOpenButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PositionTypeComponent(
            setPosition = setPosition,
            onClickPositionOpenButton = onClickPositionOpenButton
        )
        if (positionData == "담임선생님") {
            GradeSelectorComponent(
                setGrade = setGrade,
                onClickGradeOpenButton = onClickGradeOpenButton
            )
            ClassSelectorComponent(
                setClass = setClass,
                onClickClassOpenButton = onClickClassOpenButton
            )

        }
    }
}

@Preview
@Composable
private fun TeacherRegistrationSectionPre() {
    TeacherRegistrationSection(
        positionData = "담임선생님",
        setPosition = "",
        setGrade = "1학년",
        setClass = "1반",
        onClickPositionOpenButton = {},
        onClickGradeOpenButton = {}) {}
}