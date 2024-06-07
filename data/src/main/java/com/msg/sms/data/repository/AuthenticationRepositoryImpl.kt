package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.authentication.RemoteAuthenticationDataSource
import com.msg.sms.domain.model.authentication.AuthenticationFormModel
import com.msg.sms.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthenticationDataSource,
) : AuthenticationRepository {
    override suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormModel> =
        remoteDataSource.fetchAuthenticationForm().map {
            it.toAuthenticationFormModel()
        }
}