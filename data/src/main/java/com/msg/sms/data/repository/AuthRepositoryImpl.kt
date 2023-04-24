package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import com.msg.sms.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthDataSource
) : AuthRepository {
    override suspend fun gAuthLogin(body: GAuthLoginRequestModel): Flow<GAuthLoginResponseModel> {
        return flow { remoteDataSource.gAuthLogin(body = body) }
    }
}

