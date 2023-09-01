package com.msg.sms.domain.model.student.response

import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.student.request.ProjectModel

data class GetStudentForStudentModel(
    val name: String,
    val introduce: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>,
    val projects: List<ProjectModel>,
    val prizes: List<PrizeModel>
)
