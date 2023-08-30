package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.common.PrizeData
import com.msg.sms.data.remote.dto.common.ProjectData
import com.msg.sms.data.remote.dto.common.toPrizeModel
import com.msg.sms.data.remote.dto.common.toProjectModel
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
    val projectList: List<ProjectData>,
    @SerializedName("prizes")
    val awardData: List<PrizeData>
)

fun GetStudentForAnonymousResponse.toGetStudentForAnonymousModel(): GetStudentForAnonymousModel {
    return GetStudentForAnonymousModel(
        name = name,
        introduce = introduce,
        major = major,
        profileImg = profileImg,
        techStack = techStack,
        projectList = projectList.map { it.toProjectModel() },
        awardData = awardData.map { it.toPrizeModel() }
    )
}
