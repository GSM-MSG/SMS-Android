package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.common.PrizeData
import com.msg.sms.data.remote.dto.student.request.ProjectData
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
    @SerializedName("profileImgUrl")
    val profileImg: String,
    @SerializedName("techStacks")
    val techStack: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectData>,
    @SerializedName("prizes")
    val prizes: List<PrizeData>
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