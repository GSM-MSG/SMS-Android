package com.sms.presentation.main.ui.detail.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.component.text.TechStackRow
import com.msg.sms.design.util.AddBody1Title

@Composable
fun ProjectTechStacksComponent(techStackList: List<String> ) {
    AddBody1Title(titleText = "사용기술", spaceSize = 8) {
        TechStackRow(modifier = Modifier, techStack = techStackList)
    }
}