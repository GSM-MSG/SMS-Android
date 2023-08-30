package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.response.StudentModel
import java.util.UUID

data class StudentInformation(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("major")
    val profileImg: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileImg")
    val major: String,
    @SerializedName("techStacks")
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