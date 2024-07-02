package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.model.student.request.PutChangeProfileRequestModel
import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class PutChangedProfileUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(profile: PutChangeProfileRequestModel) = kotlin.runCatching {
        repository.putChangedProfile(profile = profile)
    }
}