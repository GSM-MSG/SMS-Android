package com.msg.sms.domain.model.user.response

data class MyProfileModel(
    val name: String,
    val introduce: String,
    val dreamBookFileUrl: String,
    val portfolioUrl: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: String,
    val major: String,
    val profileImg: String,
    val contactEmail: String,
    val gsmAuthenticationScore: Int,
    val formOfEmployment: String,
    val regions: List<String>,
    val militaryService: String,
    val salary: Int,
    val languageCertificates: List<LanguageCertificateModel>,
    val certificates: List<String>,
    val techStacks: List<String>,
)
