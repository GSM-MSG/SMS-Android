package com.msg.sms.domain.usecase

import com.msg.sms.domain.model.auth.request.GAuthLoginRequestData
import com.msg.sms.domain.repository.AuthRepository
import javax.inject.Inject

class GAuthLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: GAuthLoginRequestData) = kotlin.runCatching {
        authRepository.gAuthLogin(body = body)
    }
}