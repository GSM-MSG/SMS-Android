package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.MarkingBoardType
import com.msg.sms.domain.model.authentication.response.VerifyAuthenticationModel

data class VerifyAuthenticationResponse(
    val name: String,
    val score: Double,
    val grader: String?,
    val markingBoardType: String,
)

fun VerifyAuthenticationResponse.toVerifyAuthenticationResponseModel() = VerifyAuthenticationModel(
    name = this.name,
    score = this.score,
    grader = this.grader,
    markingBoardType = MarkingBoardType.valueOf(this.markingBoardType)
)