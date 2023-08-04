package com.msg.sms.domain.model.student.request

data class EnterStudentInformationModel(
    val major: String,
    val techStack: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val portfolioUrl: String,
    val contactEmail: String,
    val formOfEmployment: String,
    val gsmAuthenticationScore: Int,
    val salary: Int,
    val region: List<String>,
    val languageCertificate: List<CertificateInformationModel>,
    val militaryService: String,
    val certificate: List<String>
)
