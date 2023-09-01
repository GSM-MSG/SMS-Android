package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectKeyTaskComponent(keyTask: String, onValueChange: (value: String) -> Unit) {
    AddGrayBody1Title(titleText = "주요 작업") {
        SmsTextField(
            setText = keyTask,
            placeHolder = "저는 해당 프로젝트에 뼈를 묻었습니다.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            onValueChange = onValueChange
        ) {
            onValueChange("")
        }
    }
}

@Preview
@Composable
private fun ProjectKeyTaskComponentPre() {
    ProjectKeyTaskComponent("저는 학교에서 자습 신청, 안마의자  급식 조희, 공지 사항 파트를 개발하였습니다.", onValueChange = {})
}