package com.msg.sms.data.repository

import com.msg.sms.data.local.datasource.auth.LocalAuthDataSource
import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.toLoginModel
import com.msg.sms.data.remote.dto.auth.response.toModel
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.AccessValidationResponseModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthDataSource,
    private val localDataSource: LocalAuthDataSource,
) : AuthRepository {
    override suspend fun gAuthLogin(body: GAuthLoginRequestModel): Flow<GAuthLoginResponseModel> {
        return remoteDataSource.gAuthLogin(
            body = GAuthLoginRequest(
                code = body.code
            )
        ).map { it.toLoginModel() }
    }

    override suspend fun saveTheLoginData(data: GAuthLoginResponseModel) {
        data.let {
            localDataSource.setAccessToken(it.accessToken)
            localDataSource.setAccessTime(it.accessTokenExp)
            localDataSource.setRefreshToken(it.refreshToken)
            localDataSource.setRefreshTime(it.refreshTokenExp)
            localDataSource.setRoleInfo(it.role)
        }
    }

    override suspend fun accessValidation(): Flow<AccessValidationResponseModel> {
        return remoteDataSource.accessValidation().map { it.toModel() }
    }

    override suspend fun logout(): Flow<Unit> {
        return remoteDataSource.logout()
    }

    override suspend fun withdrawal(): Flow<Unit> {
        return remoteDataSource.withdrawal()
    }

    override suspend fun deleteLoginData() {
        localDataSource.removeAccessToken()
        localDataSource.removeAccessTime()
        localDataSource.removeRefreshToken()
        localDataSource.removeRefreshTime()
        localDataSource.removeRoleInfo()
    }

    override suspend fun getRoleInfo(): Flow<String> {
        return localDataSource.getRoleInfo()
    }
}