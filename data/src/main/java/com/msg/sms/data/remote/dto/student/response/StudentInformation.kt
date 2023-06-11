package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.StudentModel

data class StudentInformation(
    val profileImgUrl: String,
    val name: String,
    val major: String,
    val techStack: List<String>
)

fun StudentInformation.toStudentModel() =
    StudentModel(
        profileImgUrl = this.profileImgUrl,
        name = this.name,
        major = this.major,
        techStack = this.techStack
    )
