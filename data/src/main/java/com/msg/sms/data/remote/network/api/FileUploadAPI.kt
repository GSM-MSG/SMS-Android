package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.fileupload.response.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileUploadAPI {
    @Multipart
    @POST("file/image")
    suspend fun imageUpload(
        @Part file: MultipartBody.Part
    ): FileUploadResponse
}