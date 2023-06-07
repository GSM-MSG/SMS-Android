package com.msg.sms.data.remote.dto.student.response

data class StudentInformation(
    val profileImg: String,
    val name: String,
    val major: String,
    val techStack: List<String>
)
