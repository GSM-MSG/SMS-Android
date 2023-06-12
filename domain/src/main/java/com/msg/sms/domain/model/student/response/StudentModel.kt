package com.msg.sms.domain.model.student.response

data class StudentModel(
    val profileImgUrl: String,
    val name: String,
    val major: String,
    val techStack: List<String>
)
