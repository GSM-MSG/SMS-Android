package com.sms.presentation.main.ui.main.data

import com.msg.sms.domain.model.student.response.CertificationModel
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData

data class StudentDetailData(
    val name: String = "",
    val introduce: String = "",
    val dreamBookFileUrl: String = "",
    val portfolioUrl: String = "",
    val grade: Int = 0,
    val classNum: Int = 0,
    val number: Int = 0,
    val department: String = "",
    val major: String = "",
    val profileImg: String = "",
    val contactEmail: String = "",
    val gsmAuthenticationScore: Int = 0,
    val formOfEmployment: String = "",
    val regions: List<String> = listOf(),
    val militaryService: String = "",
    val salary: Int = 0,
    val languageCertificates: List<CertificationModel> = listOf(),
    val certificates: List<String> = listOf(),
    val techStacks: List<String> = listOf(),
    val awardData: List<AwardData> = listOf(),
    val projectList: List<ProjectData> = listOf()
)
