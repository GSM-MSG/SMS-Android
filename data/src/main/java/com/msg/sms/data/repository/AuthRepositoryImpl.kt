package com.msg.sms.data.repository

import com.msg.sms.data.local.datasource.auth.LocalAuthDataSource
import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.toLoginModel
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthDataSource,
    private val localDataSource: LocalAuthDataSource
) : AuthRepository {
    override suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponseModel {
        return remoteDataSource.gAuthLogin(
            body = GAuthLoginRequest(
                code = body.code
            )
        ).toLoginModel()
    }

    override suspend fun saveTheLoginData(data: GAuthLoginResponseModel) {
        data.let {
            localDataSource.setAccessToken(it.accessToken)
            localDataSource.setAccessTime(it.accessTokenExp)
            localDataSource.setRefreshToken(it.refreshToken)
            localDataSource.setRefreshTime(it.refreshTokenExp)
        }
    }
}