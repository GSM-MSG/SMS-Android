package com.msg.sms.data.remote.datasource.user

import com.msg.sms.data.remote.dto.user.response.GetProfileImageResponse
import kotlinx.coroutines.flow.Flow

interface RemoteUserDataSource {
    suspend fun getProfileImage(): Flow<GetProfileImageResponse>
}