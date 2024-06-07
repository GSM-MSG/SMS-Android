package com.msg.sms.data.remote.datasource.authentication

import com.msg.sms.data.remote.dto.athentication.AuthenticationFormResponse
import com.msg.sms.data.remote.network.api.AuthenticationAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteAuthenticationDataSourceImpl @Inject constructor(
    private val authenticationApi: AuthenticationAPI,
) : RemoteAuthenticationDataSource {
    override suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormResponse> = flow {
        emit(SMSApiHandler<AuthenticationFormResponse>().httpRequest {
            authenticationApi.fetchAuthenticationForm()
        }.sendRequest())
    }
}