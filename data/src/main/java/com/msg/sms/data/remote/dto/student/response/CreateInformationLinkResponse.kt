package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.student.response.CreateInformationLinkResponseModel
import okhttp3.internal.http.hasBody

data class CreateInformationLinkResponse(
    @SerializedName("token")
    val token: String
)

fun CreateInformationLinkResponse.toCreateInformationLinkModel() =
    CreateInformationLinkResponseModel(
        token = this.token
    )