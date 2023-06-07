package com.msg.sms.data.remote.dto.student.response

data class GetStudentListResponse(
    val content: List<StudentInformation>,
    val page: Int,
    val size: Int,
    val last: Boolean,
    val isFilter: Boolean
)
