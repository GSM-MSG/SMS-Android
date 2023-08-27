package com.msg.sms.data.remote.dto.user.response

import com.msg.sms.domain.model.user.response.ProgressModel

data class ProgressResponse(
    val start: String,
    val end: String,
)

fun ProgressResponse.toProgressModel(): ProgressModel {
    return ProgressModel(
        start = this.start,
        end = this.end
    )
}