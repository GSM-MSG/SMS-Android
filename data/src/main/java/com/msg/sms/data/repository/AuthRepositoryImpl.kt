package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.data.remote.dto.auth.request.GAuthLoginRequest
import com.msg.sms.data.remote.dto.auth.response.toLoginData
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestData
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseData
import com.msg.sms.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthDataSource
) : AuthRepository {
    override suspend fun gAuthLogin(body: GAuthLoginRequestData): GAuthLoginResponseData =
        remoteDataSource.gAuthLogin(body = GAuthLoginRequest(code = body.code)).toLoginData()
}