package com.msg.sms.data.remote.dto.teacher.request

import com.google.gson.annotations.SerializedName

data class HomeroomTeacherRequest(
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("classNum")
    val classNum: Int
)