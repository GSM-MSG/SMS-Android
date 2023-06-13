package com.msg.sms.data.local.datasource.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.msg.sms.data.local.key.AuthPreferenceKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalAuthDataSource {
    override suspend fun getAccessToken(): Flow<String> = dataStore.data.map {
        it[AuthPreferenceKey.ACCESS_TOKEN] ?: ""
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit {
            it[AuthPreferenceKey.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun removeAccessToken() {
        dataStore.edit {
            it.remove(AuthPreferenceKey.ACCESS_TOKEN)
        }
    }

    override suspend fun getAccessTime(): Flow<String> = dataStore.data.map {
        it[AuthPreferenceKey.ACCESS_TIME] ?: ""
    }

    override suspend fun setAccessTime(accessTime: String) {
        dataStore.edit {
            it[AuthPreferenceKey.ACCESS_TIME] = accessTime
        }
    }

    override suspend fun removeAccessTime() {
        dataStore.edit {
            it.remove(AuthPreferenceKey.ACCESS_TIME)
        }
    }

    override suspend fun getRefreshToken(): Flow<String> = dataStore.data.map {
        it[AuthPreferenceKey.REFRESH_TOKEN] ?: ""
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit {
            it[AuthPreferenceKey.REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun removeRefreshToken() {
        dataStore.edit {
            it.remove(AuthPreferenceKey.REFRESH_TOKEN)
        }
    }

    override suspend fun getRefreshTime(): Flow<String> = dataStore.data.map {
        it[AuthPreferenceKey.REFRESH_TIME] ?: ""
    }

    override suspend fun setRefreshTime(refreshTime: String) {
        dataStore.edit {
            it[AuthPreferenceKey.REFRESH_TIME] = refreshTime
        }
    }

    override suspend fun removeRefreshTime() {
        dataStore.edit {
            it.remove(AuthPreferenceKey.REFRESH_TIME)
        }
    }

    override suspend fun getRoleInfo(): Flow<String> = dataStore.data.map {
        it[AuthPreferenceKey.ROLE] ?: ""
    }

    override suspend fun setRoleInfo(role: String) {
        dataStore.edit {
            it[AuthPreferenceKey.ROLE] = role
        }
    }

    override suspend fun deleteRoleInfo() {
        dataStore.edit {
            it.remove(AuthPreferenceKey.ROLE)
        }
    }
}