package com.msg.sms.domain.usecase.teacher

import com.msg.sms.domain.repository.TeacherRepository
import javax.inject.Inject

class HeadOfDepartmentRegistrationUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        repository.headOfDepartment()
    }
}