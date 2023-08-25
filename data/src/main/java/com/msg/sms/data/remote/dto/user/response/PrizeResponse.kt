package com.msg.sms.data.remote.dto.user.response

import com.msg.sms.domain.model.user.response.PrizeModel

data class PrizeResponse(
    val name: String,
    val type: String,
    val date: String,
)

fun PrizeResponse.toPrizeModel(): PrizeModel{
    return PrizeModel(
        name = this.name,
        type = this.type,
        date = type
    )
}