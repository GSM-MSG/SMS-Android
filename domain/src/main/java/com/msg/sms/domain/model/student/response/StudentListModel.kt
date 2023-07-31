package com.msg.sms.domain.model.student.response

data class StudentListModel(
    val content: List<StudentModel>,
    val page: Int,
    val contentSize: Int,
    val last: Boolean,
    val totalSize: Int
)
