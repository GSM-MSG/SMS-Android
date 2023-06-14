package com.msg.sms.domain.repository

import com.msg.sms.domain.model.user.response.ProfileImageModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getProfileImage(): Flow<ProfileImageModel>
}