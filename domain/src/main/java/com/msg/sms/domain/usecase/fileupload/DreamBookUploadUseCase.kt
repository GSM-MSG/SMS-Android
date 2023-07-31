package com.msg.sms.domain.usecase.fileupload

import com.msg.sms.domain.repository.FileUploadRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class DreamBookUploadUseCase @Inject constructor(
    private val repository: FileUploadRepository
) {
    suspend operator fun invoke(file: MultipartBody.Part) = kotlin.runCatching {
        repository.dreamBookUpload(file = file)
    }
}