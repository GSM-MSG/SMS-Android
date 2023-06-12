package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.StudentListModel

data class GetStudentListResponse(
    val content: List<StudentInformation>,
    val page: Int,
    val contentSize: Int,
    val last: Boolean,
    val totalSize: Int
)

fun GetStudentListResponse.toStudentListModel() =
    StudentListModel(
        content = this.content.map { it.toStudentModel() },
        page = this.page,
        contentSize = this.contentSize,
        last = this.last,
        totalSize = this.totalSize
    )
