package com.msg.sms.domain.model.student.response

import java.util.UUID

data class StudentModel(
    val id: UUID,
    val profileImg: String,
    val name: String,
    val major: String,
    val techStack: List<String>
)
