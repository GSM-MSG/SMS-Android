package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.user.RemoteUserDataSource
import com.msg.sms.data.remote.dto.user.response.toMyProfileModel
import com.msg.sms.data.remote.dto.user.response.toProfileImageModel
import com.msg.sms.domain.model.user.response.MyProfileModel
import com.msg.sms.domain.model.user.response.ProfileImageModel
import com.msg.sms.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: RemoteUserDataSource,
) : UserRepository {
    override suspend fun getProfileImage(): Flow<ProfileImageModel> {
        return dataSource.getProfileImage().map { it.toProfileImageModel() }
    }

    override suspend fun getMyProfile(): Flow<MyProfileModel> {
        return dataSource.getMyProfile().map { it.toMyProfileModel() }
    }
}