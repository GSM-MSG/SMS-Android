package com.msg.sms.domain.repository

import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface FileUploadRepository {
    suspend fun imageUpload(file: MultipartBody.Part): Flow<FileUploadResponseModel>
}