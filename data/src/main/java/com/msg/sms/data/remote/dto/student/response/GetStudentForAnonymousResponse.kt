package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.response.GetStudentForAnonymousModel

data class GetStudentForAnonymousResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("profileImgUrl")
    val profileImg: String,
    @SerializedName("techStacks")
    val techStack: List<String>,
    @SerializedName("projects")
    val projectList: List<String>,
    @SerializedName("prizes")
    val awardData: List<String>
)

fun GetStudentForAnonymousResponse.toGetStudentForAnonymousModel(): GetStudentForAnonymousModel {
    return GetStudentForAnonymousModel(
        name = name,
        introduce = introduce,
        major = major,
        profileImg = profileImg,
        techStack = techStack,
        projectList = projectList,
        awardData = awardData
    )
}
