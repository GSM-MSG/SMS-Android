package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import java.util.*
import javax.inject.Inject

class GetStudentDetailForStudentUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(uuid: UUID) = kotlin.runCatching {
        repository.getUserDetailForStudent(uuid = uuid)
    }
}