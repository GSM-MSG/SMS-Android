package com.msg.sms.domain.usecase.auth

import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTheLoginDataUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(data: GAuthLoginResponseModel) = runCatching {
        authRepository.saveTheLoginData(data)
    }
}