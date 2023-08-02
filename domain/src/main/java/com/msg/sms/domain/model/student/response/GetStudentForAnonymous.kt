package com.msg.sms.domain.model.student.response

data class GetStudentForAnonymous(
    val name: String,
    val introduce: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>,
    val projectList: List<String>,
    val awardData: List<String>
)
