package com.msg.sms.domain.model.student.request

data class CreateInformationLinkRequestModel(
    val studentId: String,
    val periodDay: Long
)
