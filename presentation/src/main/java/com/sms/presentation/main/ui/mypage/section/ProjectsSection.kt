package com.sms.presentation.main.ui.mypage.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.mypage.component.project.ProjectComponent

@Composable
fun ProjectsSection(data: String) {
    ProjectComponent(data = data)
}

@Preview
@Composable
private fun ProjectSectionPre() {
    ProjectsSection("프로젝트 1")
}