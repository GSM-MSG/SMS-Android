package com.msg.sms.data.remote.datasource.fileupload

import com.msg.sms.data.remote.dto.fileupload.response.FileUploadResponse
import com.msg.sms.data.remote.network.api.FileUploadAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteFileUploadDataSourceImpl @Inject constructor(
    private val service: FileUploadAPI
) : RemoteFileUploadDataSource {
    override suspend fun imageUpload(file: MultipartBody.Part): Flow<FileUploadResponse> {
        return flow {
            emit(
                SMSApiHandler<FileUploadResponse>()
                    .httpRequest { service.imageUpload(file = file) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun dreamBookUpload(file: MultipartBody.Part): Flow<FileUploadResponse> {
        return flow {
            emit(
                SMSApiHandler<FileUploadResponse>()
                    .httpRequest { service.dreamBookUpload(file = file) }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }
}