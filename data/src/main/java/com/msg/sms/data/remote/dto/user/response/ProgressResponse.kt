package com.msg.sms.data.remote.dto.user.response

import com.msg.sms.domain.model.user.response.ActivityDuration

data class ProgressResponse(
    val start: String,
    val end: String,
)

fun ProgressResponse.toProgressModel(): ActivityDuration {
    return ActivityDuration(
        start = this.start,
        end = this.end
    )
}