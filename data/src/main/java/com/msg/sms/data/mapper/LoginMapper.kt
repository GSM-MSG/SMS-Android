package com.msg.sms.data.mapper

import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel

object LoginMapper {
    fun toLoginDto(data: GAuthLoginRequestModel): GAuthLoginRequest {
        return GAuthLoginRequest(
            code = data.code
        )
    }
}