package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.user.response.ActivityDuration

data class ProjectDateData(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String?
)

fun ProjectDateData.toProjectDateModel(): ActivityDuration {
    return ActivityDuration(
        start = this.start,
        end = this.end
    )
}