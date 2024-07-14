package com.msg.sms.domain.usecase.authentication

import com.msg.sms.domain.repository.AuthenticationRepository
import javax.inject.Inject

class VerifyAuthenticationUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {
    suspend operator fun invoke() = repository.verifyAuthentication()
}