package com.msg.sms.domain.usecase.auth

import com.msg.sms.domain.repository.AuthRepository
import javax.inject.Inject

class SaveRoleInfoUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching {
        repository.saveRoleInfo(role = role)
    }
}