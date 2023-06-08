package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class GetStudentListUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(page: Int, size: Int) = kotlin.runCatching {
        repository.getStudentList(page = page, size = size)
    }
}