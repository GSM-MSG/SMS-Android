package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import java.util.UUID
import javax.inject.Inject

class GetUserDetailForAnonymousUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(uuid: UUID) = kotlin.runCatching {
        repository.getUserDetail(role = "anonymous", uuid = uuid)
    }
}