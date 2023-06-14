package com.msg.sms.data.remote.dto.user.response

import com.msg.sms.domain.model.user.response.ProfileImageModel

data class GetProfileImageResponse(
    val profileImgUrl: String
)

fun GetProfileImageResponse.toProfileImageModel(): ProfileImageModel {
    return ProfileImageModel(profileImgUrl = this.profileImgUrl)
}