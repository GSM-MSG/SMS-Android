package com.msg.sms.data.remote.dto.athentication.response

import com.msg.sms.domain.model.authentication.response.FileModel

data class FileResponse(
    val name: String,
    val url: String,
) {

    fun toFileModel() = FileModel(
        name = this.name,
        url = this.url
    )
}