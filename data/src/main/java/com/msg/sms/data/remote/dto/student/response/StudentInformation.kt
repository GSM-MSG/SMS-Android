package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.StudentModel

data class StudentInformation(
    val profileImg: String,
    val name: String,
    val major: String,
    val techStack: List<String>
)

fun StudentInformation.toStudentModel() =
    StudentModel(
        profileImg = this.profileImg,
        name = this.name,
        major = this.major,
        techStack = this.techStack
    )
