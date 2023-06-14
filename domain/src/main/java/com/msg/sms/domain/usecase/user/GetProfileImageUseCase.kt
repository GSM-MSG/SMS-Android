package com.msg.sms.domain.usecase.user

import com.msg.sms.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileImageUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        repository.getProfileImage()
    }
}