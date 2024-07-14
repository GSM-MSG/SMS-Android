package com.msg.sms.domain.model.authentication.response

import com.msg.sms.domain.model.authentication.MarkingBoardType

data class VerifyAuthenticationModel(
    val name: String,
    val score: Double,
    val grader: String?,
    val markingBoardType: MarkingBoardType
)