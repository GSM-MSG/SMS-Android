package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.model.user.response.MyProfileModel
import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class PutChangedProfileUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(profile: MyProfileModel) = kotlin.runCatching {
        repository.putChangedProfile(profile = profile)
    }
}