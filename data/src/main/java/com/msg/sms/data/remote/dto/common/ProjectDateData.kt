package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.request.ProjectDateModel

data class ProjectDateData(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String?
)

fun ProjectDateData.toProjectDateModel(): ProjectDateModel {
    return ProjectDateModel(
        start = this.start,
        end = this.end
    )
}