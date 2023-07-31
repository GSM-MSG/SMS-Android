package com.msg.sms.domain.model.student.response

data class GetStudentForStudent(
    val name: String,
    val introduce: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>,
)
