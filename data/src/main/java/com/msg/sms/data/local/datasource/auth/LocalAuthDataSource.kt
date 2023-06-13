package com.msg.sms.data.local.datasource.auth

import kotlinx.coroutines.flow.Flow

interface LocalAuthDataSource {

    //    AccessToken
    suspend fun getAccessToken(): Flow<String>

    suspend fun setAccessToken(accessToken: String)

    suspend fun removeAccessToken()

    //    AccessTime
    suspend fun getAccessTime(): Flow<String>

    suspend fun setAccessTime(accessTime: String)

    suspend fun removeAccessTime()

    //    RefreshToken
    suspend fun getRefreshToken(): Flow<String>

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun removeRefreshToken()

    //    RefreshTime
    suspend fun getRefreshTime(): Flow<String>

    suspend fun setRefreshTime(refreshTime: String)

    suspend fun removeRefreshTime()

    //    Role
    suspend fun setRoleInfo(role: String)

    suspend fun getRoleInfo(): Flow<String>
}