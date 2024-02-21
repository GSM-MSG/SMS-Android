package com.msg.sms.domain.usecase.teacher

import com.msg.sms.domain.model.teacher.request.HomeroomTeacherRequestModel
import com.msg.sms.domain.repository.TeacherRepository
import javax.inject.Inject

class HomeroomRegistrationUseCase @Inject constructor(
    private val repository: TeacherRepository
){
    suspend operator fun invoke(body: HomeroomTeacherRequestModel) = kotlin.runCatching {
        repository.homeroom(
            body = body
        )
    }
}