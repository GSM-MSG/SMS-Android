package com.msg.sms.domain.usecase.stack

import com.msg.sms.domain.repository.StackRepository
import javax.inject.Inject

class GetSearchDetailStackUseCase @Inject constructor(
    private val repository: StackRepository,
) {
    suspend operator fun invoke(name: String) = runCatching {
        repository.getDetailStack(name = name)
    }
}