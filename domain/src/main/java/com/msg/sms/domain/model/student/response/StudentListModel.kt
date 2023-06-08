package com.msg.sms.domain.model.student.response

data class StudentListModel(
    val content: List<StudentModel>,
    val page: Int,
    val size: Int,
    val last: Boolean,
    val isFilter: Boolean
)
