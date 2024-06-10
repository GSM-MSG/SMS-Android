package com.msg.sms.domain.model.student.response

import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.common.ProjectModel

data class GetStudentModel(
    val name: String = "",
    val introduce: String = "",
    val portfolioUrl: String? = null,
    val grade: Int? = null,
    val classNum: Int? = null,
    val number: Int? = null,
    val department: String? = null,
    val major: String = "",
    val profileImg: String? = null,
    val contactEmail: String? = null,
    val gsmAuthenticationScore: Int? = null,
    val formOfEmployment: String? = null,
    val regions: List<String>? = null,
    val militaryService: String? = null,
    val salary: Int? = null,
    val languageCertificates: List<CertificateModel>? = null,
    val certificates: List<String>? = null,
    val techStacks: List<String> = emptyList(),
    val projects: List<ProjectModel> = emptyList(),
    val prize: List<PrizeModel> = emptyList(),
)