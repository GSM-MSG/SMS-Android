package com.msg.sms.domain.model.student.request

import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.common.PrizeModel

data class EnterStudentInformationModel(
    val major: String,
    val techStacks: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val contactEmail: String,
    val prizes: List<PrizeModel>
)
