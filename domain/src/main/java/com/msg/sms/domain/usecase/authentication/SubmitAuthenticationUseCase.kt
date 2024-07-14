package com.msg.sms.domain.usecase.authentication

import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SubmitAuthenticationUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {
    suspend operator fun invoke(formData: SubmitAuthenticationModel) =
        repository.submitAuthenticationForm(formData = formData)
}