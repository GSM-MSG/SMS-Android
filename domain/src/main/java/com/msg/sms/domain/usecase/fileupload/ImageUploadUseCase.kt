package com.msg.sms.domain.usecase.fileupload

import com.msg.sms.domain.repository.FileUploadRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageUploadUseCase @Inject constructor(
    private val repository: FileUploadRepository
) {
    suspend operator fun invoke(file: MultipartBody.Part) = kotlin.runCatching {
        repository.imageUpload(file = file)
    }
}