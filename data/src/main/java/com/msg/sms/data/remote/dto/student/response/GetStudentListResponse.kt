package com.msg.sms.data.remote.dto.student.response

import com.msg.sms.domain.model.student.response.StudentListModel

data class GetStudentListResponse(
    val content: List<StudentInformation>,
    val page: Int,
    val size: Int,
    val last: Boolean,
    val isFilter: Boolean
)

fun GetStudentListResponse.toStudentListModel() =
    StudentListModel(
        content = this.content.map { it.toStudentModel() },
        page = this.page,
        size = this.size,
        last = this.last,
        isFilter = this.isFilter
    )
