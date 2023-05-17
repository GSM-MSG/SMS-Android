package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class EnterStudentInformationUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(body: EnterStudentInformationModel) = kotlin.runCatching {
        repository.enterStudentInformation(body = body)
    }
}