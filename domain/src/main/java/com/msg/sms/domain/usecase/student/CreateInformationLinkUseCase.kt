package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.model.student.request.CreateInformationLinkRequestModel
import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class CreateInformationLinkUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(body: CreateInformationLinkRequestModel) = kotlin.runCatching {
        repository.createInformationLink(body = body)
    }
}