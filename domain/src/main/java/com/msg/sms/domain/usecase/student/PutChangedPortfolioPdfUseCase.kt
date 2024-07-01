package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PutChangedPortfolioPdfUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(file: MultipartBody.Part) = kotlin.runCatching {
        repository.putChangedPortfolioPdf(file = file)
    }
}