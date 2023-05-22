package com.msg.sms.data.remote.dto.fileupload.response

import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel

data class FileUploadResponse(
    val fileUrl: String
)

fun FileUploadResponse.toFileUploadModel() =
    FileUploadResponseModel(
        fileUrl = this.fileUrl
    )