package com.msg.sms.data.remote.dto.common

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.request.PrizeModel

data class PrizeData(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)

fun PrizeData.toPrizeModel(): PrizeModel {
    return PrizeModel(
        name = this.name,
        type = this.type,
        date = this.date
    )
}
