package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.response.GetStudentForStudent

data class GetStudentForStudentResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("classNum")
    val classNum: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("department")
    val department: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("profileImg")
    val profileImg: String,
    @SerializedName("techStack")
    val techStack: List<String>,
)

fun GetStudentForStudentResponse.toGetStudentForStudent(): GetStudentForStudent {
    return GetStudentForStudent(
        name = name,
        introduce = introduce,
        grade = grade,
        classNum = classNum,
        number = number,
        department = department,
        major = major,
        profileImg = profileImg,
        techStack = techStack
    )
}