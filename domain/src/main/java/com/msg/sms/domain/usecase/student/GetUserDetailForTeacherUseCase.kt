package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import java.util.*
import javax.inject.Inject

class GetUserDetailForTeacherUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(uuid: UUID) = kotlin.runCatching {
        repository.getUserDetailForTeacher(uuid = uuid)
    }
}