package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.common.PrizeData
import com.msg.sms.data.remote.dto.common.ProjectData
import com.msg.sms.data.remote.dto.common.toPrizeModel
import com.msg.sms.data.remote.dto.common.toProjectModel
import com.msg.sms.domain.model.student.response.GetStudentForStudentModel

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

fun GetStudentForStudentResponse.toGetStudentForStudentModel(): GetStudentForStudentModel {
    return GetStudentForStudentModel(
        name = this.name,
        introduce = this.introduce,
        grade = this.grade,
        classNum = this.classNum,
        number = this.number,
        department = this.department,
        major = this.major,
        profileImg = this.profileImg,
        techStack = this.techStack,
        projects = this.projects.map { it.toProjectModel() },
        prizes = this.prizes.map { it.toPrizeModel() },
    )
}