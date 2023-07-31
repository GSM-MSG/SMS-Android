package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.StudentModel
import java.util.UUID

data class StudentInformation(
    val id: UUID,
    val profileImg: String,
    val name: String,
    val major: String,
    val techStack: List<String>,
)

fun StudentInformation.toStudentModel() =
    StudentModel(
        id = id,
        profileImg = this.profileImg,
        name = this.name,
        major = this.major,
        techStack = this.techStack
    )