package com.msg.sms.data.remote.dto.major

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.major.MajorListModel

data class MajorListResponse(
    @SerializedName("major")
    val major: List<String>,
)

fun MajorListResponse.toMajorListModel() =
    MajorListModel(major = this.major)