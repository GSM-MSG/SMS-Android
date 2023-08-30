package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.response.StudentListModel

data class GetStudentListResponse(
    @SerializedName("content")
    val content: List<StudentInformation>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("contentSize")
    val contentSize: Int,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("totalSize")
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
