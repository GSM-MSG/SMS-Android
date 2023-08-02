package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.GetStudentForAnonymous

data class GetStudentForAnonymousResponse(
    val name: String,
    val introduce: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>,
    val projectList: List<String>,
    val awardData: List<String>
)

fun GetStudentForAnonymousResponse.toGetStudentForAnonymous(): GetStudentForAnonymous {
    return GetStudentForAnonymous(
        name = name,
        introduce = introduce,
        major = major,
        profileImg = profileImg,
        techStack = techStack,
        projectList = projectList,
        awardData = awardData
    )
}
