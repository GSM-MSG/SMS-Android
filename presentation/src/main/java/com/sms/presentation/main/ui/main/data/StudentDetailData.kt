package com.sms.presentation.main.ui.main.data

import com.msg.sms.domain.model.student.response.CertificationModel

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
    val techStacks: List<String> = listOf()
)
