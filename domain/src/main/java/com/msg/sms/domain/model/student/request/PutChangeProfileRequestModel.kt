package com.msg.sms.domain.model.student.request

import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.common.ProjectModel
import com.msg.sms.domain.model.user.response.LanguageCertificateModel

data class PutChangeProfileRequestModel(
    val name: String,
    val introduce: String,
    val portfolioUrl: String?,
    val portfolioFileUrl: String?,
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
    val projects: List<ProjectModel>,
    val prizes: List<PrizeModel>
)