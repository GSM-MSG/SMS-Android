package com.msg.sms.data.remote.datasource.fileupload

import com.msg.sms.data.remote.dto.fileupload.response.FileUploadResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface RemoteFileUploadDataSource {
    suspend fun imageUpload(file: MultipartBody.Part): Flow<FileUploadResponse>

    suspend fun dreamBookUpload(file: MultipartBody.Part): Flow<FileUploadResponse>
}