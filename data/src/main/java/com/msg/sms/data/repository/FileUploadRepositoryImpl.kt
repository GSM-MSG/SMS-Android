package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.fileupload.RemoteFileUploadDataSource
import com.msg.sms.data.remote.dto.fileupload.response.toFileUploadModel
import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
import com.msg.sms.domain.repository.FileUploadRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import javax.inject.Inject

class FileUploadRepositoryImpl @Inject constructor(
    private val dataSource: RemoteFileUploadDataSource
) : FileUploadRepository {
    override suspend fun imageUpload(file: MultipartBody.Part): Flow<FileUploadResponseModel> {
        return dataSource.imageUpload(
            file = file
        ).map { it.toFileUploadModel() }
    }

    override suspend fun dreamBookUpload(file: MultipartBody.Part): Flow<FileUploadResponseModel> {
        return dataSource.dreamBookUpload(
            file = file
        ).map { it.toFileUploadModel() }
    }
}