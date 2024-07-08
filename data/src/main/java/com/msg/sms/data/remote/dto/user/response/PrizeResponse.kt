package com.msg.sms.data.remote.dto.user.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.common.PrizeModel

data class PrizeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String,
)

fun PrizeResponse.toPrizeModel(): PrizeModel {
    return PrizeModel(
        name = this.name,
        type = this.type,
        date = this.date
    )
}