package com.msg.sms.data.remote.datasource.authentication

import com.msg.sms.data.remote.dto.athentication.request.SubmitAuthenticationFormRequest
import com.msg.sms.data.remote.dto.athentication.response.AuthenticationFormResponse
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
            authenticationApi.fetchAuthenticationForm(uuid = "bc3933a-44be-49b4-a6e7-2ecfd3f6c30a")
        }.sendRequest())
    }

    override suspend fun submitAuthenticationForm(formData: SubmitAuthenticationFormRequest): Flow<Unit> = flow {
        emit(SMSApiHandler<Unit>().httpRequest {
            authenticationApi.submitAuthenticationForm(uuid = "bc3933a-44be-49b4-a6e7-2ecfd3f6c30a", formData = formData)
        }.sendRequest())
    }
}