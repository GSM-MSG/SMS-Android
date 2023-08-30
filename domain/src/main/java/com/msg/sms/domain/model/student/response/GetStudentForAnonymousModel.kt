package com.msg.sms.domain.model.student.response

import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.student.request.ProjectModel

data class GetStudentForAnonymousModel(
    val name: String,
    val introduce: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>,
    val projectList: List<ProjectModel>,
    val awardData: List<PrizeModel>
)
