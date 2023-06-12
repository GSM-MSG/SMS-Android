package com.msg.sms.domain.model.student.response

data class GetStudentForTeacher(
    val name: String,
    val introduce: String,
    val dreamBookFileUrl: String?,
    val portfolioUrl: String?,
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
    val languageCertificates: List<CertificationModel>,
    val certificates: List<String>,
    val techStacks: List<String>
)

data class CertificationModel(
    val languageCertificateName: String,
    val score: String
)