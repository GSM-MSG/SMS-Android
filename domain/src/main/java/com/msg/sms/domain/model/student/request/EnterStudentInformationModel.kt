package com.msg.sms.domain.model.student.request

import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.common.PrizeModel

data class EnterStudentInformationModel(
    val major: String,
    val techStacks: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val contactEmail: String,
    val formOfEmployment: String,
    val gsmAuthenticationScore: Int,
    val salary: Int,
    val regions: List<String>,
    val languageCertificates: List<CertificateModel>,
    val militaryService: String,
    val certificates: List<String>,
    val projects: List<ProjectModel>,
    val prizes: List<PrizeModel>
)
