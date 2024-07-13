package com.msg.sms.domain.usecase.fileupload

import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
import com.msg.sms.domain.repository.FileUploadRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class FileUploadUseCase @Inject constructor(
    private val fileUploadRepository: FileUploadRepository,
) {
    suspend operator fun invoke(file: File): Flow<FileUploadResponseModel> =
        fileUploadRepository.fileUpload(file)
}