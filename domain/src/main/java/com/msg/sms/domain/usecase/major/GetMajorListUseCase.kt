package com.msg.sms.domain.usecase.major

import com.msg.sms.domain.repository.MajorRepository
import javax.inject.Inject

class GetMajorListUseCase @Inject constructor(
    private val repository: MajorRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.getMajorList()
    }
}